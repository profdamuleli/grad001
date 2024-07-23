# Enviro365 File Processing Application
RESTful API using Spring Boot to facilitate file data processing for Enviro365 clients. This API allows users to upload text files containing environmental data for analysis and retrieval of processed results via simple API requests. Processed data is stored persistently in an in-memory H2 database.

# Project Endpoints

- File Upload Endpoint <br>
URL : /api/upload <br>
Method: POST <br>
Description: Endpoint for clients to upload text files containing environmental data. <br>
Request Body : <br>
file: Text file containing environmental data (multipart/form-data). <br>

- Retrieve Processed Data Endpoint <br>
URL : /api/data/{id} <br> 
Method : GET <br> 
Description : Endpoint to retrieve processed data by its unique identifier. <br> 
Path Parameter : <br> 
{id} : Technical ID of the processed data. <br>

- List All Data Endpoint  <br>
URL : /api/data  <br>
Method : GET  <br>
Description : Endpoint to retrieve a list of all processed data entries.  <br>

# Documentation
- `http://localhost:8091/swagger-ui/index.html`
