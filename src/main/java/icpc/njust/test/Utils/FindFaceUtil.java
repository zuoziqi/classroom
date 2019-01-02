package icpc.njust.test.Utils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class FindFaceUtil {
    public static String detectFace(String image) throws Exception {
        //image = image.split('data:image/png;base64,')[1];
        StringBuffer buffer = new StringBuffer();
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, byte[]> byteMap = new HashMap<String, byte[]>();
        map.put("api_key", "NzYeCu98GK840P4He1jOOfRLlWPQZF1x");
        map.put("api_secret", "IDCUAzFp8Ade-3NH8to4ajzlVBamy2tT");
        map.put("return_attributes",
                "gender,age,glass,headpose,smiling,ethnicity");
        map.put("image_base64", image);
        String string = null;
        try {
            byte[] bacd = post(url, map, byteMap);
            string = new String(bacd);
            System.out.println(string);
            // 获取faces下的所有属性
            /*
            JSONArray faceArray = (JSONArray) JSONObject.fromObject(string)
                    .get("faces");
            List<Face> faceList = new ArrayList<Face>();
            for (int i = 0; i < faceArray.size(); i++) {
                JSONObject faceObject = (JSONObject) faceArray.get(i);
                JSONObject attributesObject = faceObject
                        .getJSONObject("attributes");

                Face face = new Face();

                // face.setFaceId(faceObject.getString("face_id"));
                // 年龄
                face.setAgeValue(attributesObject.getJSONObject("age").getInt(
                        "value"));
                // 性别
                face.setGenderValue(genderCovent(attributesObject
                        .getJSONObject("gender").getString("value")));
                // 人种
                face.setRaceValue(raceCovent(attributesObject.getJSONObject(
                        "ethnicity").getString("value")));
                // 微笑程度
                face.setSmilingValue(attributesObject.getJSONObject("smile")
                        .getDouble("value"));
                face.setSmilingValue(attributesObject.getJSONObject("smile")
                        .getDouble("value"));
                // 是否戴眼镜
                face.setGlass(glassCovent(attributesObject.getJSONObject(
                        "glass").getString("value")));
                // 人脸姿势(抬头，摇头，转头)
                face.setHeadpose_pitch(attributesObject.getJSONObject(
                        "headpose").getDouble("yaw_angle"));
                face.setHeadpose_roll(attributesObject
                        .getJSONObject("headpose").getDouble("pitch_angle"));
                face.setHeadpose_yaw(attributesObject.getJSONObject("headpose")
                        .getDouble("roll_angle"));
                faceList.add(face);
            }
            if (1 == faceList.size()) {
                buffer.append("共检测到").append(faceList.size()).append("张人脸")
                        .append("\n\n");
                for (Face face : faceList) {
                    buffer.append(face.getRaceValue()).append("人种，");
                    buffer.append(face.getGenderValue()).append(",");
                    buffer.append(face.getAgeValue()).append("岁左右");
                    buffer.append("\n").append("是否佩戴眼镜：")
                            .append(face.getGlass());
                }
            } else if (faceList.size() > 1) {
                buffer.append("共检测到").append(faceList.size())
                        .append("张人脸,按脸部中心位置从右至左依次为").append("\n\n");
                for (Face face : faceList) {
                    buffer.append(face.getRaceValue()).append("人种，");
                    buffer.append(face.getGenderValue()).append(",");
                    buffer.append(face.getAgeValue()).append("岁左右");
                    buffer.append("\n").append("是否佩戴眼镜：")
                            .append(face.getGlass());
                }
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return buffer.toString();
        return string;
    }


    /*
        private static String genderCovent(String gender) {
            String result = "男性";
            if (gender.equals("Female")) {

                result = "女性";
            } else if (gender.equals("Male")) {
                result = "男性";
            }
            return result;
        }


        private static String raceCovent(String race) {
            String result = "黄色";// "Asian/White/Black"
            if (race.equals("Asian")) {

                result = "黄色";
            } else if (race.equals("White")) {
                result = "白色";
            } else if (race.equals("Black")) {
                result = "黑色";
            }
            return result;

        }

        public static String glassCovent(String galss) {
            String result = "不佩戴眼镜";// "None	不佩戴眼镜       Dark	佩戴墨镜    Normal 佩戴普通眼镜"
            if (galss.equals("None")) {

                result = "不佩戴眼镜  ";
            } else if (galss.equals("Dark")) {
                result = "佩戴墨镜";
            } else if (galss.equals("Normal")) {
                result = "佩戴普通眼镜";
            }
            return result;

        }
    */
    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();

    protected static byte[] post(String url, HashMap<String, String> map,
                                 HashMap<String, byte[]> fileMap) throws Exception {
        HttpURLConnection conne;
        URL url1 = new URL(url);
        conne = (HttpURLConnection) url1.openConnection();
        conne.setDoOutput(true);
        conne.setUseCaches(false);
        conne.setRequestMethod("POST");
        conne.setConnectTimeout(CONNECT_TIME_OUT);
        conne.setReadTimeout(READ_OUT_TIME);
        conne.setRequestProperty("accept", "*/*");
        conne.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundaryString);
        conne.setRequestProperty("connection", "Keep-Alive");
        conne.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key
                    + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if (fileMap != null && fileMap.size() > 0) {
            Iterator fileIter = fileMap.entrySet().iterator();
            while (fileIter.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter
                        .next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\""
                        + fileEntry.getKey() + "\"; filename=\"" + encode(" ")
                        + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = conne.getResponseCode();
        try {
            if (code == 200) {
                ins = conne.getInputStream();
            } else {
                ins = conne.getErrorStream();
            }
        } catch (SSLException e) {
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while ((len = ins.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; ++i) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(random
                    .nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_"
                            .length())));
        }
        return sb.toString();
    }

    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
}