import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {NotificationService} from '../../service/notification.service';
import {UserService} from '../../service/user.service';
import {User} from '../../models/User';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  public profileEditForm: FormGroup;

  constructor(private dialogRef: MatDialogRef<EditUserComponent>,
              private fb: FormBuilder,
              private notificationService: NotificationService,
              @Inject(MAT_DIALOG_DATA) public data,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.profileEditForm = this.createProfileForm();
  }

  createProfileForm(): FormGroup {
    return this.fb.group({
      firstName: [
        this.data.user.firstName,
        Validators.compose([Validators.required])
      ],
      lastName: [
        this.data.user.lastName,
        Validators.compose([Validators.required])
      ],
      biography: [
        this.data.user.biography,
        Validators.compose([Validators.required])
      ]
    });
  }

  submit(): void {
    this.userService.updateUser(this.updateUser())
      .subscribe(() => {
        this.notificationService.showSnackBar('User updated successfully');
        this.dialogRef.close();
      });
  }

  private updateUser(): User {
    this.data.user.firstName = this.profileEditForm.value.firstName;
    this.data.user.lastName = this.profileEditForm.value.lastName;
    this.data.user.biography = this.profileEditForm.value.biography;
    return this.data.user;
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
