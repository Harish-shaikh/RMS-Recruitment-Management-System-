RMS(API-Documetions)

User API Endpoints
1. Create a New User
•	URL: /api/v1/user/signup
•	Method: POST
•	Request Body: UserDto object
•	Description: Creates a new user with the provided details.
•	Response: UserDto object of the created user.
•	Response Status Codes:
•	201 Created - User created successfully.
•	500 Internal Server Error - If an error occurs during user creation.
2. Get All Jobs
•	URL: /api/v1/user/jobs
•	Method: GET
•	Description: Retrieves all available jobs.
•	Response: List of JobsDto objects.
•	Response Status Codes:
•	200 OK - Jobs retrieved successfully.
•	500 Internal Server Error - If an error occurs during job retrieval.
3. Upload Resume and Create Profile
•	URL: /api/v1/user/uploadresume/{userId}
•	Method: POST
•	Request Parameters:
•	userId - ID of the user for whom the profile is being created.
•	Request Body: Multipart file (resume file)
•	Description: Uploads the resume file, parses it to extract user details, and creates a new user profile.
•	Response: ProfileDto object of the created profile.
•	Response Status Codes:
•	200 OK - Profile created successfully.
•	500 Internal Server Error - If an error occurs during profile creation or resume parsing.
4. Apply for a Job
•	URL: /api/v1/user/jobs/apply/{jobsId}/{userId}
•	Method: POST
•	Path Parameters:
•	jobsId - ID of the job to apply for.
•	userId - ID of the user applying for the job.
•	Description: Allows a user to apply for a specific job.
•	Response: Success message indicating successful job application.
•	Response Status Codes:
•	200 OK - Job application successful.
•	404 Not Found - If the specified job or user is not found.
•	500 Internal Server Error - If an error occurs during job application.
5. User Authentication
•	URL: /api/v1/user/login
•	Method: POST
•	Request Body: AuthRequest object containing user credentials (username and password).
•	Description: Authenticates the user based on the provided credentials.
•	Response: AuthenticationResponse object containing authentication token.
•	Response Status Codes:
•	200 OK - User authenticated successfully.
•	401 Unauthorized - If authentication fails.
•	500 Internal Server Error - If an error occurs during authentication.
________________________________________
Admin API Endpoints
1. Create a New Job
•	URL: /api/v2/admin/jobs
•	Method: POST
•	Request Body: JobsDto object
•	Description: Creates a new job with the provided details.
•	Response: JobsDto object of the created job.
•	Response Status Codes:
•	201 Created - Job created successfully.
•	500 Internal Server Error - If an error occurs during job creation.
2. Get All Users
•	URL: /api/v2/admin/alluser
•	Method: GET
•	Description: Retrieves all users.
•	Response: List of UserDto objects.
•	Response Status Codes:
•	200 OK - Users retrieved successfully.
•	500 Internal Server Error - If an error occurs during user retrieval.
3. Get Single User
•	URL: /api/v2/admin/user/{userId}
•	Method: GET
•	Path Parameters:
•	userId - ID of the user to retrieve.
•	Description: Retrieves details of a single user by ID.
•	Response: UserDto object containing details of the requested user.
•	Response Status Codes:
•	200 OK - User retrieved successfully.
•	404 Not Found - If the specified user is not found.
•	500 Internal Server Error - If an error occurs during user retrieval.
4. Get Single Job
•	URL: /api/v2/admin/jobs/{jobId}
•	Method: GET
•	Path Parameters:
•	jobId - ID of the job to retrieve.
•	Description: Retrieves details of a single job by ID.
•	Response: JobsDto object containing details of the requested job.
•	Response Status Codes:
•	200 OK - Job retrieved successfully.
•	404 Not Found - If the specified job is not found.
•	500 Internal Server Error - If an error occurs during job retrieval.
________________________________________

