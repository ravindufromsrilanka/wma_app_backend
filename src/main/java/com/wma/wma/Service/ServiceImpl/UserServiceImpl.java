package com.wma.wma.Service.ServiceImpl;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import com.wma.wma.Configuration.Configuration;
import com.wma.wma.Entity.GeneralUserProfile;
import com.wma.wma.Entity.MemberType;
import com.wma.wma.Entity.OTP;
import com.wma.wma.Entity.UserLogin;
import com.wma.wma.Exceptions.ErrorException;
import com.wma.wma.Payload.Request.OtpVerificationRequest;
import com.wma.wma.Payload.Request.UserLoginRequest;
import com.wma.wma.Payload.Request.UserRegistrationRequest;
import com.wma.wma.Payload.Response.SuccessResponse;
import com.wma.wma.Repositories.GeneralUserProfileRepository;
import com.wma.wma.Repositories.MemberTypeRepository;
import com.wma.wma.Repositories.OtpRepository;
import com.wma.wma.Repositories.UserLoginRepository;
import com.wma.wma.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MemberTypeRepository memberTypeRepository;

    @Autowired
    GeneralUserProfileRepository generalUserProfileRepository;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    OtpRepository otpRepository;

    String otpCode;

    @Override
    public SuccessResponse userRegistration(UserRegistrationRequest request) {

        MemberType type = memberTypeRepository.findById(Long.valueOf(request.getMemberType()))
                .orElseThrow(() -> (new ErrorException("Invalid member type", "Invalid member type")));
        GeneralUserProfile gup = generalUserProfileRepository.getAllByTelephone(request.getTelephone());
        if (gup == null) {
            if (request.getEmail().matches(Configuration.VALID_EMAIL_ADDRESS_REGEX)) {
//                if (request.getTelephone().matches(Configuration.VALIDATE_TELEPHONE_NUMBER)) {
                if (request.getPassword().matches(Configuration.VALID_PASSWORD_REGEX)) {


                    boolean b = sendOtp(request);

                    if (b) {

                        GeneralUserProfile newGup = new GeneralUserProfile();
                        newGup.setName(request.getName());
                        newGup.setEmail(request.getEmail());
                        newGup.setTelephone(request.getTelephone());
                        generalUserProfileRepository.save(newGup);

                        UserLogin userLogin = new UserLogin();
                        userLogin.setTelephone(request.getTelephone());
                        userLogin.setType(type);
                        userLogin.setPassword(request.getPassword());
                        userLogin.setGeneralUserProfile(newGup);
                        userLogin.setVerification(false);
                        userLoginRepository.save(userLogin);

                        OTP otp = new OTP();
                        otp.setOtp(otpCode);
                        otp.setState(false);
                        otp.setGeneralUserProfile(newGup);
                        otpRepository.save(otp);

                        SuccessResponse response = new SuccessResponse();
                        response.setResponseCode(200);
                        response.setResponse("User Registration Successful");
                        return response;
                    } else {
                        throw new ErrorException("Something went wrong please try again later", "otp verification failed");
                    }
                } else {
                    throw new ErrorException("Password should contains minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character", "invalid password");
                }
            } else {
                throw new ErrorException("The given telephone number is invalid.Number can only consist numbers only.", "Invalid telephone number");
            }
        } else {
            throw new ErrorException("The given email is invalid.please check and enter valid email", "invalid email type");
        }
//        }else{
//            throw new ErrorException("The given number already registered in WMA" , "The telephone number already registered");
//        }
    }

    @Override
    public SuccessResponse otpVerification(OtpVerificationRequest request) {
        GeneralUserProfile gup = generalUserProfileRepository.getAllByTelephone(request.getTelephone());
        if (gup != null) {
            OTP otp = otpRepository.getAllByGeneralUserProfile(gup);
            if (otp != null) {
                otp.setState(true);
                otpRepository.save(otp);

                UserLogin userLogin = userLoginRepository.getByGeneralUserProfile(gup);
                userLogin.setVerification(true);
                userLoginRepository.save(userLogin);

                SuccessResponse response = new SuccessResponse();
                response.setResponseCode(200);
                response.setResponse("otp verification successful");
                return response;
            } else {
                throw new ErrorException("User does not have a otp to verify", "otp can not found");
            }
        } else {
            throw new ErrorException("Can not find a user for this telephone number", "Can not find a user for this telephone number");
        }
    }

    @Override
    public SuccessResponse userLogin(UserLoginRequest request) {

        UserLogin login = userLoginRepository.getAllByTelephone(request.getUsername());

        if (login != null) {
            if (login.getPassword() == request.getPassword()) {
                SuccessResponse response = new SuccessResponse();
                response.setResponseCode(200);
                response.setResponse("Login successful");
                return response;
            } else {
                throw new ErrorException("invalid username or password. Please check and try again", "Login failed");
            }
        } else {
            throw new ErrorException("invalid username or password. Please check and try again", "Login failed");
        }
    }

    private boolean sendOtp(UserRegistrationRequest request) {

        VonageClient client = VonageClient.builder().apiKey("8d545839").apiSecret("vdasar0xZSSKHeSE").build();

        String code = randomCodeGenerator();

        otpCode = code;

        TextMessage message = new TextMessage("WMA Society",
                request.getTelephone(),
                "Use this 6-digit value to verify your number:\n" +
                        "\n" +
                        "Code :" + code
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
            return true;
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
            return false;
        }
    }

    private String randomCodeGenerator() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }
}
