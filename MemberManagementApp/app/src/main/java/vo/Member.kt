package vo

import android.os.Parcel
import android.os.Parcelable

class Member(var name:String?, var age:Int, var location:String?) : Parcelable {

    constructor(parcel: Parcel):this(parcel.readString(), parcel.readInt(), parcel.readString())

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeString(name)
        parcel?.writeInt(age)
        parcel?.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Member> {
        override fun createFromParcel(parcel: Parcel): Member {
            return Member(parcel)
        }

        override fun newArray(size: Int): Array<Member?> {
            return arrayOfNulls(size)
        }
    }
}