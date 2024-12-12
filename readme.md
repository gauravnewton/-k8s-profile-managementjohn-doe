Coding Assignment: Kubernetes Profile Management API
Objective
Develop a Spring Boot application that enables users to create Kubernetes (K8s) profiles by
uploading configuration files. The application parses the file to extract metadata, displays
it to the UI for review, and then saves the profile metadata and file location to MongoDB. It
also supports CRUD operations on the profiles.
Assignment Requirements
Functional Requirements
1. Upload Config File (**Sample at the end of document)
   a. Accepts a Kubernetes configuration file (YAML format) via a multipart
   request.
   b. Parses the file to extract metadata like:
   i. clusterName
   ii. username (from the users section of the file)
   c. Returns the extracted metadata in JSON format for the UI to display.
2. Create Profile
   a. Accepts:
   i. Metadata (clusterAlias, description, etc.) from the user.
   ii. The file location (from the upload step).
   b. Saves the metadata in MongoDB and the file in a unique directory on the
   filesystem
3. Fetch All Profiles
   a. Returns a list of all profiles with their metadata.
4. Fetch a Specific Profile
   a. Returns metadata and file location for a specific profile.
5. Read Specific Profile’s config file
   a. Returns config file for a specific profile.
6. Update Profile
   a. Allows updating the metadata (e.g., clusterAlias, description) or
   replacing the uploaded configuration file.
   b. Updates the file on the filesystem and the corresponding metadata in
   MongoDB.
7. Delete Profile
   a. Deletes the profile metadata from MongoDB and the corresponding file from
   the filesystem.
   Frameworks & Tools
   • Use Spring Boot for the backend.
   • Use MongoDB for storing profile metadata.
   Evaluation Criteria
1. Code Quality
   a. Clean, modular code with well-defined layers (Controller, Service,
   Repository).
2. YAML Parsing
   a. Robust and reliable extraction of metadata.
3. File Handling
   a. Secure and efficient file storage.
4. Database Design
   a. Efficient use of MongoDB for metadata storage.
5. CRUD Operations
   a. Complete and functional implementation.
6. Error Handling
   a. Comprehensive error messages and HTTP status codes.
7. Test Cases:
   a. Ensure that unit and integration tests are present and instructions for
   running them are included.
8. Documentation
   a. Clear API documentation using Swagger or OpenAPI.
   Submission Guidelines
1. GitHub Repository
   a. Candidates should create a public GitHub repository for their assignment.
   b. The repository name should follow this convention:
   k8s-profile-management-{candidate-name} (e.g., k8s-profile-managementjohn-doe).
2. Repository Structure
   a. The repository must follow a clean and organized structure
3. GitHub Access
   a. After pushing the code, make the repository public.
   b. Share the repository link with the submission email.
4. Deadline
   a. 48 Hours
   **An example of an AWS EKS Kubernetes configuration YAML file that can be used as a
   sample:
   apiVersion: v1
   clusters:
- cluster:
  server: https://EXAMPLE-ENDPOINT.gr7.us-west-2.eks.amazonaws.com
  certificate-authority-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk... #
  Truncated for simplicity
  name: eks-sample-cluster
  contexts:
- context:
  cluster: eks-sample-cluster
  user: eks-sample-user
  name: eks-sample-context
  current-context: eks-sample-context
  kind: Config
  preferences: {}
  users:
- name: eks-sample-user
  user:
  exec:
  apiVersion: client.authentication.k8s.io/v1beta1
  command: aws
  args:
- eks
- get-token
- --cluster-name
- eks-sample-cluster
- --region
- us-west-2