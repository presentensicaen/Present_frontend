package fr.ensicaen.present.present.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pierr on 31/12/2017.
 */

public class CallModel implements Parcelable {
    @SerializedName("id")
    private String _id;
    @SerializedName("duration")
    private int _duration;
    @SerializedName("groups")
    private ArrayList<String> _groups;

    public CallModel(String _id, int _duration, ArrayList<String> _groups) {
        this._id = _id;
        this._duration = _duration;
        this._groups = _groups;
    }

    public CallModel(String _id, int _duration) {
        this._id = _id;
        this._duration = _duration;
        this._groups = null;
    }

    @Override
    public String toString() {
        return "CallModel{" +
                "_id='" + _id + '\'' +
                ", _duration='" + String.valueOf(_duration) + '\'' +
                ", _groups='" + _groups.toString() + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(_id);
        out.writeInt(_duration);
        out.writeList(_groups);
    }


    public static final Parcelable.Creator<CallModel> CREATOR = new Parcelable.Creator<CallModel>() {
        public CallModel createFromParcel(Parcel in) {
            return new CallModel(in);
        }

        public CallModel[] newArray(int size) {
            return new CallModel[size];
        }
    };

    public final class CallObjectHolder {
        @SerializedName("call")
        private CallModel _call;

        public CallModel getCall() {
            return _call;
        }
    }

    public String getDisplayId() {
        return _id;
    }

    private CallModel(Parcel in) {
        _id = in.readString();
        _duration = in.readInt();
        _groups = new ArrayList<>();
        in.readList(_groups, null);
    }


}
