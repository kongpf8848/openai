package openai;

public class Constants {

    public static final String OPENAI_API_KEY;
    public static final String AZURE_API_KEY;
    public static final String AZURE_END_POINT;
    public static final String AZURE_DEPLOYMENT_ID;

    static {
        OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
        AZURE_API_KEY = System.getenv("AZURE_API_KEY");
        AZURE_END_POINT = System.getenv("AZURE_END_POINT");
        AZURE_DEPLOYMENT_ID = System.getenv("AZURE_DEPLOYMENT_ID");
    }

}
