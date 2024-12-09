<h1>SpringBoot AWS S3 Filestore</h1>

<h2>Test Endpoints</h2>

<h4>1. Upload File</h4>

Endpoint: POST /api/v1/s3/upload

Body: Form-data (key: file, value: file to upload)


<h4>2. Download File</h4>

Endpoint: GET /api/v1/s3/download/{fileName}

Response: File content as application/octet-stream.


<h4>3. List Files</h4>

Endpoint: GET /api/v1/s3/list

Response: List of file names in the bucket.


<h4>4. Delete File</h4>

Endpoint: DELETE /api/v1/s3/delete/{fileName}

Response: Confirmation message.