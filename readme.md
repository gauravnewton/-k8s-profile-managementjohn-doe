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
      ```yaml
      apiVersion: v1
      clusters:
      - cluster:
         certificate-authority-data: VVSG5kbktsamRVCkZ6ajl1SzdlNDlTamFINjFwYnJPUUcyUGk4MU92UTFvPQ0V3L1gxY2g4NGU4a0RPcUpFbHN4TCs2ZHZIdW1weEp6SEI3VlBMR0IKYXNwZDJPd0lQWjdzVnNtVW9PUitTS096Y3UxbEZEM2hKbkNHVVY3VnFQeWxsUFJTK1BLcFNnSDdDNE42UENHUQpxRE1GV2Y0V2F0REtML09ZdExwZWNQa2pUWTZhb0EzR1dKRnZGaDJ0VzlSVHBGSWNEMDRmWERLM3RKdkViOURSeGlJQVdiR3lYYb3BmCmdCaFNsZDhUWUdWaVFLMGYwWU1jS0dhd2VpQlhxYit5Wlh3RGJRTjQ5a2dnOUFBUlkwRzhCWjg5OStuakRzQm0KSUZiL241WXYvWSs1Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0KLS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURCVENDQWUyZ0F3SUJBZ0lJQWJLQ3pwY3NWTkF3Rk84ejdtdFcxRUEKWUVFK3NrbGV5Qm5TclBsVWVlNDJIMSszTHBIa2oyMUxuVE5uem5zSXY4R0RqOWFQOU5hODhXRnd2bWlNZGtJRgp2Wi9XMDUvVjBkMU1iWkNaS1hhMGtEM2ZTRTJoQWdNQkFBR2pXVEJYTUE0R0ExVWREd0VCL3dRRUF3SUNwREFQCkJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUamtuSEIyV1Z4SWJ2WWJLNkdEbnkzemhCL0h6QVYKQmdOVkhSRUVEakFNZ2dwcmRXSmxjbTVsZEdWek1BMEdDU3FHU0liM0RRRUJDd1VBQTRJQkFRQ2NnVVJka3pPOApxTDhLbSt4QVVqNnBiSitvbnlPVnplNVBBMlNSQ3FTVGxJUzlJc0dWZENoOGNBY3NuMk1TZ1V3VllMMHNXQjBDCkI3eTk4L3FoZWdGcWFzFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TkRFd01qRXhNREkzTlRkYUZ3MHpOREV3TVRreE1ETXlOVGRhTUJVeApFekFSQmdOVkJBTVRDbXQxWW1WeWJtVjBaWE13Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLCkFvSUJBUUMxb29BWkZDSUVXT2d4WGMrTS9zZ3FDTXRmSldBRTJQdkJjbHNkR3FDL2paazhlRlRSckppTXFZLysKUzl0K0labnZYeDAxTjNVVXViMWxYWjhUN1VWMEprTzBoVXlKcWEybGQ4Z1g5VzVhd1B2UVRFeUsxdTV4TFJOcQo2UFFqWVlhNTBpWmNaTGR4dHBQb2ZvYUFMSFVzdzNaRXNyU1Z6VzN2V3ZMK0R6cWRkaHh3Q1jJzUVFRMm1jSOXV1bmYxK0J
         server: https://8FDE7667FB3DA3D76DA58D4B651EF537.gr2.eu-west-3.eks.amazonaws.com
        name: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
      contexts:
      - context:
         cluster: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
         user: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
        name: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
      current-context: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
      kind: Config
      preferences: {}
      users:
      - name: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
         user:
            exec:
               apiVersion: client.authentication.k8s.io/v1beta1
               args:
               - --region
               - eu-west-3
               - eks
               - get-token
               - --cluster-name
               - abcd-eks-cluster
               - --output
               - json
               command: aws
       ```