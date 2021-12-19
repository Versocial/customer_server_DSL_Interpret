package vlang.interpreter;
/**
 * 用户信息
 */
public class clientInfo {
        long ID;
        String name;
        String bill;
        String birth;
        public clientInfo(long clientID){
           ID=clientID;
        }
        public String get(String key){
            return "<'" +key+"' of user "+ID+">";
        }
}
