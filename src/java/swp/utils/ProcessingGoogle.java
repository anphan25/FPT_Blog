
package swp.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;



public class ProcessingGoogle
{
    //Biến này dùng để chứa CLIENT_ID mà google cấp để cho GoogleIdTokenVerifier làm việc
    private final String CLIENT_ID = "229851668671-rehm8b9h7e190bhmtvdmf24p19g39p3d.apps.googleusercontent.com";
    private String email, name, picUrl;
    private boolean checkingstatus = true;
    
    public ProcessingGoogle(String id_Token) throws GeneralSecurityException, IOException
    {
        //GoogleIdTokenVerifier purify = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new )
        /*GoogleIdTokenVerifier purifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID)).build(); //google doc suck using the community shit instead. Purifier 1 failed to do so*/
        
        
        
        GoogleIdTokenVerifier purifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID)).build();
        //build 1 cái gì đó nằm ngoài kiến thức của tôi
        GoogleIdToken banhkem = purifier.verify(id_Token);
        //dùng những gì vừa build xác thực cái idtoken khi được truyền vào
        if(purifier.verify(banhkem))//nếu idtoken là hàng real
        {
            //dùng bánh kem để load và giải mã cái idtoken
            GoogleIdToken.Payload payday2 = banhkem.getPayload();
            //lấy thông tin từ bánh kem
            this.email = payday2.getEmail();
            this.name = (String) payday2.get("name");
            this.picUrl = (String)payday2.get("picture");
        }
        else
        {
            this.checkingstatus = false;
        }
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public boolean isCheckingstatus()
    {
        return checkingstatus;
    }
    
    public String getPicUrl()
    {
        return picUrl;
    }
    
}
    
