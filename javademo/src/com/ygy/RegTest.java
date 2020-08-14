package com.ygy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {
    public static void main(String[] args) {
        String str="2020-07-09 00:43:05.885 - [INFO] [/cache/delete] [gly] [127.0.0.1] [2] [\"AUS:MEETING:40283e81732f3d8101732f4b2cdb0012:ENTER_MAIN\",null] --- {\"success\":true,\"code\":0,\"message\":\"成功\",\"data\":null}\n";
        System.out.println(infoRegEx(str));
    }
    public static boolean infoRegEx(String str){
        String regEx = "([0-9- :.]*) - \\[([A-Z]*)\\] \\[([\\w/]*)\\] \\[([a-z]*)\\] \\[([0-9.]*)\\].*";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
