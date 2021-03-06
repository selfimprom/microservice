namespace java com.study.thrift.user

struct UserInfo {
    1:i32 id,
    2:string username,
    3:string password,
    4:string realname,
    5:string mobile,
    6:string email,
    7:string intro,
    8:i32 stars
}
service UserService {
    UserInfo getUserById(1:i32 id);
    UserInfo getUserByName(1:string name);
    UserInfo getTeacherById(1:i32 id);
    void registerUser(1:UserInfo userInfo);
}