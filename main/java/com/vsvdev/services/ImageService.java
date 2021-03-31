package com.vsvdev.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.vsvdev.entity.ImageModel;
import com.vsvdev.entity.Post;
import com.vsvdev.entity.User;
import com.vsvdev.exception.ImageNotFoundException;
import com.vsvdev.repository.ImageRepository;
import com.vsvdev.repository.PostRepository;
import com.vsvdev.repository.UserRepository;

@Service
public class ImageService {
	public static final Logger LOG = LoggerFactory.getLogger(ImageService.class);
	private final ImageRepository imageRepo;
	private final UserRepository userRepo;
	private final PostRepository postRepo;

	@Autowired
	public ImageService(ImageRepository imageRepo, UserRepository userRepo, PostRepository postRepo) {
		this.imageRepo = imageRepo;
		this.userRepo = userRepo;
		this.postRepo = postRepo;
	}

	public ImageModel uploadImageToUser(MultipartFile file, Principal principal) throws IOException {
		User user = getUserByPrincipal(principal);
		LOG.info("Uploading image " + user.getUsername());
		ImageModel userProfileImage = imageRepo.findByUserId(user.getId()).orElse(null);
		if (!ObjectUtils.isEmpty(userProfileImage)) {
			imageRepo.delete(userProfileImage);
		}
		ImageModel imageModel = new ImageModel();
		imageModel.setUserId(user.getId());
		imageModel.setImageBytes(compressBytes(file.getBytes()));
		imageModel.setName(file.getOriginalFilename());
		return imageRepo.save(imageModel);
	}

	public ImageModel uploadImageToPost(MultipartFile file, Principal principal, Long postId) throws IOException {
		User user = getUserByPrincipal(principal);
		Post post = user.getPosts().stream().filter(p -> p.getId().equals(postId)).collect(toSinglePostCollector());
		ImageModel imageModel = new ImageModel();
		imageModel.setPostId(post.getId());
		imageModel.setImageBytes(compressBytes(file.getBytes()));
		imageModel.setName(file.getOriginalFilename());
		LOG.info("Uploading image " + post.getId());
		return imageRepo.save(imageModel);
	}

	public ImageModel getImageToUser(Principal principal) {
		User user = getUserByPrincipal(principal);
		ImageModel imageModel = imageRepo.findByUserId(user.getId()).orElse(null);
		if (!ObjectUtils.isEmpty(imageModel)) {
			imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));
		}
		return imageModel;
	}

	public ImageModel getImageToPost(Long postId) {
		ImageModel imageModel = imageRepo.findByPostId(postId)
				.orElseThrow(() -> new ImageNotFoundException("Cannot find image"));
		if (!ObjectUtils.isEmpty(imageModel)) {
			imageModel.setImageBytes(decompressBytes(imageModel.getImageBytes()));
		}
		return imageModel;
	}

	private byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}

			outputStream.close();
		} catch (IOException ex) {
			LOG.error("Cannot compress bytes");
		}
		System.out.println("Compressed Image Byte size: " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	private static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException | DataFormatException ex) {
			LOG.error("Can't decompress bytes");
		}
		return outputStream.toByteArray();
	}

	private <T> Collector<T, ?, T> toSinglePostCollector() {
		return Collectors.collectingAndThen(Collectors.toList(), list -> {
			if (list.size() != 1) {
				throw new IllegalStateException();
			}
			return list.get(0);
		});
	}

	private User getUserByPrincipal(Principal principal) {
		String userName = principal.getName();
		return userRepo.findUserByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found " + userName));
	}
}
