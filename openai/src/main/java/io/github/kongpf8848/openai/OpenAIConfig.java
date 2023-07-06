package io.github.kongpf8848.openai;

public class OpenAIConfig {
    public static String BASE_URL="https://api.openai.com/v1/";
    public static int API_TYPE= Constants.TYPE_OPENAI;
    public static String OPENAI_API_KEY="";
    public static String AZURE_API_KEY="";
    public static String AZURE_DEPLOYMENT_NAME="";
    public static String AZURE_API_VERSION="2023-03-15-preview";

    public static boolean DEBUG=true;

    public static String getUrl(String path){
        if(API_TYPE==Constants.TYPE_OPENAI){
            return BASE_URL+path;
        }else if(API_TYPE==Constants.TYPE_AZURE){
            return BASE_URL+"openai/deployments/"+AZURE_DEPLOYMENT_NAME+"/"+path+"?api-version="+AZURE_API_VERSION;
        }
        return "";
    }

}
