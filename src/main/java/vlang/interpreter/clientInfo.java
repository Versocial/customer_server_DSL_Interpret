package vlang.interpreter;
/**
 * 用户信息
 */
public class clientInfo {
        long ID;
        String name;
        String bill;
        String birth;

    /**
     * 构造方法。如果连接数据库，这里可以使用clientID查询对应用户生成用户信息。
     * @param clientID 用户id
     */
    public clientInfo(long clientID){
           ID=clientID;
        }

    /**
     * 获取该用户的某种信息。
     * @param key 信息名（如”姓名“）
     * @return 对应信息（如”张三）
     */
    public String get(String key){
            String ans= "<'" +key+"' of user "+ID+">";
            return ans;
        }
}
