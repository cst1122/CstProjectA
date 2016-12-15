package com.example.dllo.project_a_cst.my_class;

/**
 * Created by dllo on 16/12/1.
 */

public class MyConstants {
    public static final String BEARING_URL
            = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0" +
            "&channel=wandoujia&operator=3&method=baidu.ting.ugcfriend.getList&format" +
            "=json&param=MdQHiv%2F%2BBSYTl39VXcn8dESPvw4rM3naxQSdeb7JiGSv9pqRpzZMYcxA%" +
            "2FqvopkR2NgdsRojMb6i2CxNCpB%2F98g%3D%3D&timestamp=1480142598&sign=462064952b8866d85f5c007d7a2cf674";

    public static final String BEARING_ADAPTER_TYPE_TWO = "http://ugcpic.qianqian.com/ugcdiy/pic/bos_ugcclient_1480068621cb19d8888af5e454772a460d763beeb6.jpg@w_350,h_170,o_1";

    public static final String LIST_FRAGMENT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2";

    public static final String RECOMMEND_FRAGMENT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=musicsybutton&operator=3&method=baidu.ting.plaza.index&cuid=1D999D9D7637E5FD3633492941572AE7";

    public static final String SONG_LIST_FRAGMENT_HEAT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=%2BAwtWRF72CKYAtqDM2Nf8%2BnLj%2BSz8CUk%2BX2WKgMOmeMrHrs12iANZHUXkJ0fW6VTGoOS7HkbFcae4BQ%2Fa%2Fqhhj7j%2FzBRYFSZNHFxF%2F%2BX8egRYVMgBQJlUjefWo2mDG6l&timestamp=1480054307&sign=6e8763041d038759eb7fb750b4c92769";
    public static final String SONG_LIST_NEW_HEAT_URL_LEFT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=";
    public static final String SONG_LIST_NEW_HEAT_URL_RIGHT = "&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";

    public static final String SONG_LIST_FRAGMENT_NEW_URL="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=EizM6Vat4Il0Cw3Ks69f0ZB7PduqSUmBTbbnWSfhRYZwU1Fv32cEck2NWBK60ourk1m2zM84o20UlSFkpICY%2BXh53eS%2FgAmRhOp2c44SjT3KQSduG5QZ8sv1Htdr6tVj&timestamp=1480076353&sign=d8c0485cdc629c15c9f2cffb3219e5b2";

    public static final String VIDEO_FRAGMENT_NEW_URL= "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    public static final String VIDEO_FRAGMENT_HEAT_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.0.0&channel=360safe&operator=3&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8";

    public static final String SINGER_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getList&format=json&order=1&limit=12&offset=0&area=0&sex=0&abc=&from=ios&version=5.2.1&from=ios&channel=appstore";

    public static final String MY_MUSIC_LIST_LEFT = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=";

    public static final String MY_MUSIC_LIST_RIGHT = "&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";

    public static final String SONG_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";


    // 广播注册

    public static final String MUSIC_PLAY = "com.example.dllo.project_a_cst.music_play";
    public static final String MUSIC_PAUSE = "com.example.dllo.project_a_cst.music_pause";
    public static final String MUSIC_PLAY_NEXT = "com.example.dllo.project_a_cst.music_play_next";
    public static final String MUSIC_PLAY_LAST = "com.example.dllo.project_a_cst.music_play_last";
    public static final String MUSIC_PLAY_SHUNXU = "com.example.dllo.project_a_cst.music_play_shunxu";
    public static final String MUSIC_PLAY_DANQU = "com.example.dllo.project_a_cst.music_play_danqu";
    public static final String MUSIC_PLAY_XUNHUAN = "com.example.dllo.project_a_cst.music_play_xunhuan";
    public static final String MUSIC_PLAY_SUIJI = "com.example.dllo.project_a_cst.music_play_suiji";

    public static final String SINGER = "歌手";
    public static final String MUSIC_NAME = "歌曲名";
    public static final String MUSIC_TIME = "时长";
    public static final String PICTURE = "图片";
    public static final String MUSIC_ID = "歌曲id";
    public static final String MUSIC_LRC = "歌词";
    public static final String MUSIC_PROGRESS = "进度";
    public static final String MUSIC_URL_LIST = "歌曲地址集合";
    public static final String PLAY_BEN_DI_MUSIC = "播放本地音乐";
    public static final String PLAY_LIST_MUSIC = "播放列表音乐";
    public static final String DRAG = "拖拽";
    public static final String COMMAND = "command";
    public static final String IS_MUSIC_PLAYING = "音乐是否播放";
    public static final String IS_PLAYING = "是否播放";
    public static final String LRC_URL = "歌词网址";
    public static final String MUSIC_INFORMATION = "歌曲信息";
    public static final String MUSIC_DURATION = "歌曲长度";
    public static final String MUSIC_PICTURE = "歌曲图片";
    public static final String PICTURE_URL = "图片网址";
}
