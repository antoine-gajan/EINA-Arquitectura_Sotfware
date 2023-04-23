package com.example.mvvm;

import android.text.TextUtils;
import android.util.Patterns;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class AppViewModel extends BaseObservable {

    // creating object of Model class
    private Model model;

    /*// string variables for
    // toast messages
    private String successMessage = "User successfully created";
    private String errorMessage = "This user can't be created";

    @Bindable

    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public String getUserNombre() {
        return model.getNombre();
    }

    public void setUserEmail(String nombre) {
        model.setNombre(nombre);
        notifyPropertyChanged(BR.userNombre);
    }

    @Bindable
    public int getUserEdad() {
        return model.getEdad();
    }

    public void setUserEdad(int edad) {
        model.setEdad(edad);
        notifyPropertyChanged(BR.userEdad);
    }

    @Bindable
    public int getUserApellido() {
        return model.getEdad();
    }
    public void setUserApellido(String apellido) {
        model.setApellido(apellido);
        notifyPropertyChanged(BR.userApellido);
    }

    // constructor of ViewModel class
    */public AppViewModel() {

        // instantiating object of
        // model class
        this.model = Model.get_instance();
    }/*

    // actions to be performed
    // when user clicks
    // the LOGIN button
    public void onButtonClicked() {
        if (isValid())
            setToastMessage(successMessage);
        else
            setToastMessage(errorMessage);
    }

    // method to keep a check
    // that variable fields must
    // not be kept empty by user
    public boolean isValid() {
        return !TextUtils.isEmpty(getUserNombre()) && getUserEdad() > 0;
    }*/
}
