package com.example.projecthrm.utils;

public interface ResponseString {
    String SUCCESS = "Thành công";
    String WRONG_LIST="Không có dữ liệu";
    String WRONG_NAME="name tồn tại";
    String WRONG_ID="ID không tồn tại";
    interface Login{
        String LOGIG_SUCCESS = "đăng nhập thành công";
        String WRONG_PASSWORD = "mật khẩu sai";
        String WRONG_ACCOUNT= "tài khoản chưa active";
        String WRONG_EMAIL = "email không tồn tại";
    }
    interface register{
        String REGISTER_SUCCESS ="đăng ký thành công";
        String REGISTER_FAILURE="đăng ký thất bại";
        String WRONG_EMAIL = "email đã tồn tại";
        String WRONGPRESENTER = "mã giới thiệu không tồn tại";
    }
    interface otp{
        String WRONG_EMAIL = "email không tồn tại";
        String OTP_FAILURE="sai OTP";
        String OTP_SUCCESS=" OTP đúng";

    }
    interface changePassword{
        String WRONG_PASSWORD = "mật khẩu sai";
        String CHANGEPASSWORD = "đổi thành công";
        String WRONG_EMAIL = "email không tồn tại";
        String WRONG_PASSWORDNEW = "mật khẩu trùng với mật kẩu cũ";
    }
    interface permission{
        String WRONG_NAME="name tồn tại";
        String WRONG_ID="ID không tồn tại";

    }
}
