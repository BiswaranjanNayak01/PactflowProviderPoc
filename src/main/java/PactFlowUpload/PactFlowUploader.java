package PactFlowUpload;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;

public class PactFlowUploader {
    private static final String PACT_BROKER_URL = "https://andolasoft.pactflow.io";
    private static final String PACT_BROKER_TOKEN = "0n2qLV7daRXgyNZgWQ0n9w";
    private static final String PATH_OPENAPI="OpenApiFiles/openapi_sampleBiswa.yaml";

    private static final String Provider_Name="UserService";

    public static void main(String[] args) {
        File openApiFile = new File(PATH_OPENAPI);
        try {
            uploadOpenApiSpec(openApiFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void uploadOpenApiSpec(File openApiFile) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(PACT_BROKER_URL + "/contracts/provider/"+Provider_Name+"/versions/1.0.1");
        uploadFile.setHeader("Authorization", "Bearer " + PACT_BROKER_TOKEN);

        FileEntity fileEntity = new FileEntity(openApiFile, ContentType.APPLICATION_OCTET_STREAM);
        uploadFile.setEntity(fileEntity);

        try (CloseableHttpResponse response = httpClient.execute(uploadFile)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 201) {
                System.out.println("OpenAPI spec uploaded successfully.");
            } else {
                System.out.println("Failed to upload OpenAPI spec. Status code: " + statusCode);
            }
        }
    }
}
