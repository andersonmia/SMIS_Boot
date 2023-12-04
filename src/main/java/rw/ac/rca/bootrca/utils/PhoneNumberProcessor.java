package rw.ac.rca.bootrca.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PhoneNumberProcessor {

    public String formatPhoneNumber(int countryCode, int phoneNumber) {
        String phoneNumberString = String.valueOf(phoneNumber);
        String countryCodeString = String.valueOf(countryCode);
        return "+" +countryCodeString +" "+ phoneNumberString;
    }
}
