package com.gunvant.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gunvant.binding.LoginForm;
import com.gunvant.binding.UnlockForm;
import com.gunvant.binding.UserForm;
import com.gunvant.entity.CityMaster;
import com.gunvant.entity.CountryMaster;
import com.gunvant.entity.StateMaster;
import com.gunvant.entity.UserAccountEntity;
import com.gunvant.repository.CityRepository;
import com.gunvant.repository.CountryRepository;
import com.gunvant.repository.StateRepository;
import com.gunvant.repository.UserAccountRepository;
import com.gunvant.util.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {
	@Autowired
	private UserAccountRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired 
	private EmailUtils emailUtil;
	
	
	@Override
	public String signUp(UserForm user) {
		UserAccountEntity userAcc = new UserAccountEntity();

		BeanUtils.copyProperties(user, userAcc);

		userAcc.setStatus("locked");

		// todo generate
		userAcc.setPassword(generatedPwd());
		UserAccountEntity savedUser = userRepo.save(userAcc);
		
		// to do logic to send email
		String email  = user.getEmail();
		String subject = "User Registration - Verification";
		
		String fileName = "/UNLOCK-ACCOUNT-MAIL-TEMPLATE.txt";
		String body = readMailBodyContent( fileName, userAcc);

		
		boolean isSent = emailUtil.sendEmail(email, subject, body);
		
		if(savedUser.getUserId() != null && isSent) {
			return "Success";
		}
//		userAcc.setFirstName(user.getFirstName());
//		userAcc.setLastName(user.getLastName());
//		userAcc.setDob(user.getDob());
//		userAcc.setGender(user.getGender());
//		userAcc.setEmail(user.getEmail());
//		userAcc.setPhno(user.getPhno());
//		userAcc.setCountry(user.getCountry());
//		userAcc.setState(user.getState());
//		userAcc.setCity(user.getCity());

		return "FAIL";
	}

	private String generatedPwd() {
		int leftLimit = 48; // 0
		int rightLimit = 122; // z
		int targetStringLength = 6;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}
	
	
	private String readMailBodyContent(String fileName , UserAccountEntity entity) {
		
		String mailBody = null;
		try {
			StringBuffer sb = new StringBuffer();
			FileReader fileReader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fileReader);
			String line  = br.readLine();
			
			while(line!=null) {
				sb.append(line); // appending data 
				line = br.readLine(); //readimg next line data
				}
			mailBody = sb.toString();
			
			mailBody = mailBody.replace("{FNAME}",entity.getFirstName());
			mailBody = mailBody.replace("{LNAME}",entity.getLastName());
			mailBody = mailBody.replace("{EMAIL}",entity.getEmail());
			mailBody = mailBody.replace("{TEMP-PWD}",entity.getPassword());
			mailBody = mailBody.replace("{PWD}", entity.getPassword());
			
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public String signIn(LoginForm login) {

		UserAccountEntity entity = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());

		if (entity == null) {
			return "Invalid Credentials";
		}

		if (entity != null && entity.getState().equals("locked")) {
			return " Your Account is Locked";
		}

		return "Success";

		// String email = login.getEmail();
//
//		if (userRepo.equals(email)) {
//			UserAccountEntity user = userRepo.getByEmail(email);
//			if (user.getStatus().equals("unlocked")) {
//				return "Welcome to Ashok It";
//			}
//			return "Your Account is Locked";
//
//		}
//		return "Email is not Available";

	}

	@Override
	public String forgotPwd(String email) {
		UserAccountEntity entity = userRepo.findByEmail(email);
		
		if(entity == null) { 
			return "Please check your email address";
			}
		
		// todo  email sendig 
		String fileName  = "RECOVER-PASSWORD-TEMPLATE.txt";
		String to = email;
		String subject = "Recovery Password";
		String body = readMailBodyContent(fileName,entity);
		
		boolean sendEmail = emailUtil.sendEmail(to, subject, body);
		if( sendEmail) {
			return "Recover Password  sent on   recover Email Address";
		}
		return "Please Try again";
	}

	@Override
	public String accUnlock(UnlockForm unlock) {

		UserAccountEntity user = userRepo.findByEmail(unlock.getEmail());
		
		if(!(unlock.getNewPwd().equals(unlock.getConfirmNewPwd()))) {
			return "Password and Confirm password should be same";
		}
		
		if(user == null ) {
			return "Incorrect  Temporary password";
		}
		user.setPassword(unlock.getNewPwd());
		user.setStatus("unlocked");
		userRepo.save(user);
		
		return "Account Unlocked";
//		if (user.getPassword().equals(unlock.getTemPwd())) {
//			user.setPassword(unlock.getNewPwd());
//			user.setStatus("unlocked");
//			userRepo.save(user);
//			return "Account unlocked , Please proceed with login";
//		}
//		return "Incorrect Temporary Password";
	}

	@Override
	public Map<Integer, String> getCountry() {
		List<CountryMaster> countryList = countryRepo.findAll();
		Map<Integer, String> maps = new HashMap<Integer, String>();
		for (CountryMaster country : countryList) {
			maps.put(country.getCountryId(), country.getCountryName());
		}
		return maps;
	}

	@Override
	public Map<Integer, String> getState(Integer countryId) {
		List<StateMaster> states = stateRepo.findByCountryId(countryId);
		Map<Integer, String> maps = new HashMap<Integer, String>();
		for (StateMaster state : states) {
			maps.put(state.getStateId(), state.getStateName());
		}
		return maps;
	}

	@Override
	public Map<Integer,String> getCity(Integer stateId) {

		List<CityMaster> cities = cityRepo.findByStateId(stateId);
		Map<Integer, String> maps = new HashMap<Integer, String>();
		for (CityMaster city : cities) {
			maps.put(city.getCityId(), city.getCityName());
		}
		return maps;
	}

	// given email id is available or not
	@Override
	public String emailCheck(String email) {
		UserAccountEntity user = userRepo.findByEmail(email);
		if (user == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

}
