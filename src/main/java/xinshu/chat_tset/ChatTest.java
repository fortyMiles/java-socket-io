package xinshu.chat_tset;

/**
 * Hello world!
 *
 */

import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatTest 
{
	
	static Socket socket;
	
	public static void startChat(){
		try {
			socket = IO.socket("http://127.0.0.1:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
        	  public void call(Object... args) {
        	    socket.emit("foo", "hi");
        	    login("18857453090");
        	    //socket.disconnect();
        	  }

        	}).on("event", new Emitter.Listener() {

        	  public void call(Object... args) {}

        	}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

        	  public void call(Object... args) {}

        	}).on("chat message", new Emitter.Listener() {

				public void call(Object... args) {
					// TODO Auto-generated method stub
					JSONObject obj = (JSONObject)args[0];
					System.out.println(obj);
				}
			});
        	socket.connect();
 
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void login(String username){
		JSONObject obj = new JSONObject();
		try {
			obj.put("name", username);
            // could add server obj.put("binary", new byte[42]);
			socket.emit("login", obj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void main( String[] args ) throws URISyntaxException
    {
   	
        System.out.println( "Hello World!" );
        System.out.println( "Waiting for server!" );
		ChatTest.startChat();
    }

}
