package com.smtlink.transferprotocoldemo;

import com.smtlink.transferprotocolsdk.Protocols;

import java.util.Arrays;
import java.util.List;

/**
 * 设置点击的Buttons
 */
public class ProtocolsHeadUtil {

    public final static String SET_MSG = "set_Msg";

    public final static String SET_DIAL = "set_Dial";

    public final static String GET_THEME_REMIND = "get_ThemeRemind";//GET,76
    public final static String SET_THEME_REMIND = "set_ThemeRemind";
    public final static String DELETE_THEME_REMIND = "delete_ThemeRemind";//SET,76
    public final static String GET_GROUP_GPS = "get_Group_Gps";//GET,65
    public final static String GET_SINGLE_GPS = "get_Single_Gps";//GET,101

    /**
     * Button List
     */
    public final static List<String> HEADS = Arrays.asList(
            Protocols.GET0, Protocols.GET10, Protocols.GET11, Protocols.GET12, Protocols.GET13, Protocols.GET14, Protocols.GET15, Protocols.GET16, Protocols.GET17, Protocols.GET18, Protocols.GET21, Protocols.GET22,
            Protocols.GET23, Protocols.GET26, Protocols.GET27, Protocols.GET28,
            Protocols.GET61, Protocols.GET64, Protocols.GET65, Protocols.GET66, Protocols.GET69, Protocols.GET70, Protocols.GET71, Protocols.GET72, Protocols.GET73, Protocols.GET74,
            Protocols.GET75, Protocols.GET77, Protocols.GET78, Protocols.GET79, Protocols.GET80, Protocols.GET81, Protocols.GET82, Protocols.GET84, Protocols.GET85, Protocols.GET86, Protocols.GET87, Protocols.GET88, Protocols.GET89, Protocols.GET90, Protocols.GET101,

            Protocols.SET10, Protocols.SET11, Protocols.SET12, Protocols.SET13, Protocols.SET15, Protocols.SET19, Protocols.SET20, Protocols.SET21, Protocols.SET30, Protocols.SET44,
            Protocols.SET45, Protocols.SET46, Protocols.SET47, Protocols.SET68, Protocols.SET72, Protocols.SET73, Protocols.SET74, Protocols.SET75, Protocols.SET77, Protocols.SET78, Protocols.SET79, Protocols.SET80,
            Protocols.SET81, Protocols.SET82, Protocols.SET83, Protocols.SET84, Protocols.SET85, Protocols.SET87, Protocols.SET88, Protocols.SET89, Protocols.SET90, Protocols.SET91, Protocols.SET92,

            SET_MSG, SET_DIAL, GET_THEME_REMIND, SET_THEME_REMIND, DELETE_THEME_REMIND,

            GET_GROUP_GPS, GET_SINGLE_GPS
    );

}
