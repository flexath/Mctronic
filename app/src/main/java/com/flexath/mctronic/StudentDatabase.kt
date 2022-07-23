package com.flexath.mctronic

class StudentDatabase {
    var fullName: String? = null
    var userName: String? = null
    var currentYear: String? = null
    var rollNumber: String? = null
    var email: String? = null
    var mobileNumber: String? = null
    var password: String? = null

    constructor() {}
    constructor(fullname: String?, username: String?,year: String?,roll: String?, email: String?, phoneNo: String?, password: String?) {
        this.fullName = fullname
        this.userName = username
        this.currentYear =  year
        this.rollNumber = roll
        this.email = email
        this.mobileNumber = phoneNo
        this.password = password
    }
}