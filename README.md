# SHrMbcnhhjK6ekJgDLPT9FwbeKa2PSqK
# 9KpkICtEnUgcGAUp

	curl "https://test.api.amadeus.com/v1/security/oauth2/token" \
	     -H "Content-Type: application/x-www-form-urlencoded" \
	     -d "grant_type=client_credentials&client_id={client_id}&client_secret={client_secret}"
	
	curl "https://test.api.amadeus.com/v1/security/oauth2/token" -H "Content-Type: application/x-www-form-urlencoded" -d "grant_type=client_credentials&client_id=&client_secret="
	
	
	C:\Users\sangbinlee9>curl "https://test.api.amadeus.com/v1/security/oauth2/token" -H "Content-Type: application/x-www-form-urlencoded" -d "grant_type=client_credentials&client_id=&client_secret="
	
	        {
	            "type": "amadeusOAuth2Token",
	            "username": "sangbinlee9@gmail.com",
	            "application_name": "flight booking app",
	            "client_id": "",
	            "token_type": "Bearer",
	            "access_token": "",
	            "expires_in": 1799,
	            "state": "approved",
	            "scope": ""
	        }# air
