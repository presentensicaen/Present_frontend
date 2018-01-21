package fr.ensicaen.present.present.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jueast on 30/12/17.
 */

public class UserModel {

    @SerializedName("name")
    private String _name;
    @SerializedName("lastName")
    private String _lastName;
    @SerializedName("id")
    private String _id;

    public UserModel(String name, String lastName, String id) {
        this._name = name;
        this._lastName = lastName;
        this._id = id;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "_name='" + _name + '\'' +
                ", _lastName='" + _lastName + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }

    public String getDisplayName() {
        return _name;
    }

    public String getId() {
        return _id;
    }

    public final class UserObjectHolder {
        @SerializedName("user")
        private UserModel _user;

        public UserModel getUser() {
            return _user;
        }
    }
}