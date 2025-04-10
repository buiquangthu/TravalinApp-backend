package com.bqt.flight_booking_server.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống không xác định"),
    DATABASE_CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi kết nối cơ sở dữ liệu"),
    EXTERNAL_SERVICE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi dịch vụ bên thứ ba"),

    // ---------------------------
    // LỖI ĐẦU VÀO (4xx)
    // ---------------------------
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Yêu cầu không hợp lệ"),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "Thiếu tham số bắt buộc: {param}"),
    INVALID_FORMAT(HttpStatus.BAD_REQUEST, "Định dạng dữ liệu không đúng"),

    // Validation
    USERNAME_INVALID(HttpStatus.BAD_REQUEST, "Tên đăng nhập phải có ít nhất %d ký tự"),
    PASSWORD_INVALID(HttpStatus.BAD_REQUEST, "Mật khẩu phải có ít nhất %d ký tự"),
    EMAIL_INVALID(HttpStatus.BAD_REQUEST, "Email không hợp lệ"),
    PHONE_INVALID(HttpStatus.BAD_REQUEST, "Số điện thoại không hợp lệ"),
    DOB_INVALID(HttpStatus.BAD_REQUEST, "Tuổi phải từ %d trở lên"),

    // ---------------------------
    // LỖI XÁC THỰC (4xx)
    // ---------------------------
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "Chưa xác thực"),
    UNAUTHORIZED(HttpStatus.FORBIDDEN, "Không có quyền truy cập"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "Thông tin đăng nhập không chính xác"),
    ACCOUNT_LOCKED(HttpStatus.FORBIDDEN, "Tài khoản tạm khóa"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token hết hạn"),
    INVALID_OTP(HttpStatus.BAD_REQUEST, "Mã OTP không hợp lệ hoặc đã hết hạn"),

    // ---------------------------
    // LỖI NGHIỆP VỤ (4xx)
    // ---------------------------
    // Người dùng
    USER_EXISTED(HttpStatus.CONFLICT, "Người dùng đã tồn tại"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng"),

    // Chuyến bay
    FLIGHT_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy chuyến bay"),
    FLIGHT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Chuyến bay đã tồn tại"),
    FLIGHT_CONFLICT(HttpStatus.CONFLICT, "Xung đột dữ liệu chuyến bay"),
    SEAT_UNAVAILABLE(HttpStatus.BAD_REQUEST, "Hết chỗ trống"),

    // hang bay
    AIRLINE_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy hãng hàng không"),

    //San bay
    AIRPORT_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy sân bay"),
    // Đặt vé
    BOOKING_EXISTS(HttpStatus.CONFLICT, "Đặt chỗ đã tồn tại"),
    BOOKING_NOT_FOUND(HttpStatus.NOT_FOUND, "Không tìm thấy đặt chỗ"),
    BOOKING_EXPIRED(HttpStatus.GONE, "Đặt chỗ đã hết hạn"),

    // Thanh toán
    PAYMENT_FAILED(HttpStatus.BAD_REQUEST, "Thanh toán thất bại"),
    PAYMENT_CONFLICT(HttpStatus.CONFLICT, "Xung đột thanh toán");

    private final HttpStatus statusCode;
    private final String message;

    ErrorCode(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String formatMessage(Object... args) {
        return String.format(message, args);
    }
}
