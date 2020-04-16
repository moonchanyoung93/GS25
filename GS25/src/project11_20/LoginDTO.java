package project11_20;

public class LoginDTO {
			String userid, password, phone;

			public String getUserid() {
				return userid;
			}

			public void setUserid(String userid) {
				this.userid = userid;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public String getPhone() {
				return phone;
			}

			public void setPhone(String phone) {
				this.phone = phone;
			}

			@Override
			public String toString() {
				return "LoginDTO [userid=" + userid + ", password=" + password + ", phone=" + phone + "]";
			}

			public LoginDTO(String userid, String password) {
				this.userid = userid;
				this.password = password;
			}

			public LoginDTO() {
			}
			
			

}
