package com.smtlink.transferprotocoldemo;

public class TestDialFun {

    private void test(int i) {
        switch (i) {
/************************************************************** W1表盘相关 ********************************************************************/
//            case ProtocolsHeadUtil.SET_DIAL://推送大数据(包括表盘,主题,应用(游戏)等)
//                mPushState.setText("正在读取数据...");
//                mPushState.setVisibility(View.VISIBLE);
//
//                /*
//                 * 注意：
//                 * 以下示例 w1_swatch_1.bin文件、w1_swatch_2001.bin针对的是项目名为W1的设备，如你的项目没有对应的.bin文件请向SDK提供方索要。
//                 * 调试时请把示例对应的所有.bin文件替换为你自己的，不要直接推送与项目不符的.bin文件，会导致设备加载资源出错。
//                 */
//
//                //下面使用assets文件夹中文件进行示范。通常情况下APP文件获取应该在服务器下载或手机本地存储中。
//
////                    ////////////////////////推送 预制(固定)表盘使用代码 START////////////////////////
////                    /*
////                     * 设置说明：
////                     * 1. 预制(固定)表盘序号取值范围大于“0”小于“2001”，  例: w1_swatch_1.bin, 取1为序号.
////                     */
////                    String dialPath = "dial/w1_swatch_1.bin";//1.预制(固定)表盘文件
////                    String programObject = "1";//序号
////                    byte[] bytes = FileUtils.byteArraysFromAssets(MainActivity.this, dialPath);
////                    ////////////////////////推送 预制(固定)表盘使用代码 END////////////////////////
//
//                ////////////////////////推送 相册(自定义)表盘使用代码 START////////////////////////
//                /*
//                 * 设置说明：
//                 *
//                 * 2.相册(自定义)表盘序号取值范围大于等于 "2001".   例: w1_swatch_2001.bin, 取2001为序号.
//                 *
//                 *  相册表盘中的"时间"icon位置可移动到表盘 中间或4个角: 左上"01",右上"02",左下"03",右下"04",居中"05"
//                 *  序号需要拼接成:"200101",加上"01"表示显示位置为左上,其他同样拼接. (注意：项目不需要这个"时间"icon就默认“200101”，如若不知是否需要，请向SDK提供方咨询)
//                 *
//                 *  相册表盘可以替换原表盘.bin文件中的"时间"icon(设备端会提供多种颜色的icons_xxx.bin文件)
//                 *  也可以替换模板表盘.bin文件中的预览和背景图
//                 *
//                 *  预览图 previewBitmap: 截取在APP界面view中显示的效果图,需裁剪为宽高(例如W1项目设备为：242*131，如若不知，请向SDK提供方索要) 的 Bitmap, 参考图片: dial/replace_img/w1_preview.jpg
//                 *  背景图 bgBitmap: 从手机相册或拍照选取,需裁剪为设备屏幕分辨率大小(例如W1项目设备为：368*194，如若不知，请向SDK提供方索要) 的 Bitmap, 参考图片: dial/replace_img/w1_bg.jpg
//                 *
//                 *  项目编码item_code: 用于区分不同项目的逻辑处理(如若不知，请向SDK提供方索要)
//                 *
//                 */
//                //String dialPath = "dial/w1_swatch_2001.bin";//2.相册(自定义)表盘模板文件
//                String dialPath = "dial/z75_swatch_2001.bin";//2.相册(自定义)表盘模板文件
//                String programObject = "200101";//序号后01为"时间"icon位置左上
//                byte[] bytes = FileUtils.byteArraysFromAssets(MainActivity.this, dialPath);//不替换表盘模板内容可直接推送bytes
//                ////////////////下面代码是替换表盘模板内容////////////////
//                //programObject = "200102";//序号后02改为"时间"icon位置右上(如果没有提供多种颜色的icons_xxx.bin文件，就默认 programObject = “200101”)
//                //预览图(请替换为你的项目所需要宽高的.png图片)
//                byte[] previewDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/w1_preview.png");
//                Bitmap previewBitmap = FileUtils.getBitmapFromBytes(previewDataBytes);
//                //背景图(请替换为你的项目所需要屏幕分辨率大小的.png图片)
//                byte[] bgDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/w1_bg.png");
//                Bitmap bgBitmap = FileUtils.getBitmapFromBytes(bgDataBytes);
//                //"时间"icon(请替换为你的项目所需要的.bin文件，如果没有提供就是不需要或者不支持 iconDataBytes = null 即可)
//                byte[] iconDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/icons/w1/number_icons_white.bin");
//                //项目编码(请替换为你的项目编码，如若不知，请向SDK提供方索要)
//                int item_code = 2012;//2012，2112
//                int preview_width = 242;//预览图的宽
//                int preview_height = 131;//预览图的高
//                int bg_width = 368;//背景图的宽
//                int bg_height = 194;//背景图的高
//                //设置表盘的项目编码，预览、背景图宽高信息(如若不知有几张预览图、以及对应宽高信息，请咨询SDK提供方)
//                DialSizeInfo dialSizeInfo = null;//DialSizeInfo API请参见doc文档中说明
//                //只有1张预览图的new DialSizeInfo如下选择4个参数的构造方法即可
//                dialSizeInfo = new DialSizeInfo(item_code, preview_width, preview_height, bg_width, bg_height);
//
//                /*
//                 * 替换相册表盘模板文件内容
//                 * 1. 只替换预览和背景图 参数preview2 为 null， iconDataBytes 为 null
//                 * 2. 只替换"时间"icon, previewBitmap、参数preview2和bgBitmap 为 null
//                 * 3. 都替换内容都添加(不需要预览图2 preview2Bitmap = null)
//                 * 4. 都不替换直接推送bytes, 不需要调用replaceAlbumDialData
//                 */
//                bytes = manager.replaceAlbumDialData(bytes, previewBitmap, null, bgBitmap, iconDataBytes, dialSizeInfo);
//                ////////////////////////推送 相册(自定义)表盘使用代码 END////////////////////////
//
//                //开始推送
//                if (bytes != null) {
//                    /*
//                     * bytes 推送的数据.
//                     * ProgramType.DIAL 推送的数据类型为表盘.
//                     * programObject 序号
//                     */
//                    //manager.cmdGet67(bytes, ProgramType.DIAL, programObject);//为防止推送示例.bin与当前设备不匹配，默认注释方法调用
//                    //注: 推送较大数据时, 设备会有缓存擦除等准备操作, 数据越大操作耗时越久, 所以触发推送时可添加一个progress提示，例如：正在读取文件数据...
//                } else {
//                    mPushState.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity.this, "读取数据错误", Toast.LENGTH_SHORT).show();
//                }
//                break;

/************************************************************** z51，z75表盘相关 ********************************************************************/
//            case ProtocolsHeadUtil.SET_DIAL://推送大数据(包括表盘,主题,应用(游戏)等)
//                mPushState.setText("正在读取数据...");
//                mPushState.setVisibility(View.VISIBLE);
//
//                /*
//                 * 注意：
//                 * 以下示例 z51_swatch_1.bin文件、z51_swatch_2001.bin针对的是项目名为z51的设备，如你的项目没有对应的.bin文件请向SDK提供方索要。
//                 * 调试时请把示例对应的所有.bin文件替换为你自己的，不要直接推送与项目不符的.bin文件，会导致设备加载资源出错。
//                 */
//
//                //下面使用assets文件夹中文件进行示范。通常情况下APP文件获取应该在服务器下载或手机本地存储中。
//
////                    ////////////////////////推送 预制(固定)表盘使用代码 START////////////////////////
////                    /*
////                     * 设置说明：
////                     * 1. 预制(固定)表盘序号取值范围大于“0”小于“2001”，  例: z51_swatch_1.bin, 取1为序号.
////                     */
////                    String dialPath = "dial/z51_swatch_1.bin";//1.预制(固定)表盘文件
////                    String programObject = "1";//序号
////                    byte[] bytes = FileUtils.byteArraysFromAssets(MainActivity.this, dialPath);
////                    ////////////////////////推送 预制(固定)表盘使用代码 END////////////////////////
//
//                ////////////////////////推送 相册(自定义)表盘使用代码 START////////////////////////
//                /*
//                 * 设置说明：
//                 *
//                 * 2.相册(自定义)表盘序号取值范围大于等于 "2001".   例: z1_swatch_2001.bin, 取2001为序号.
//                 *
//                 *  相册表盘中的"时间"icon位置可移动到表盘 中间或4个角: 左上"01",右上"02",左下"03",右下"04",居中"05"
//                 *  序号需要拼接成:"200101",加上"01"表示显示位置为左上,其他同样拼接. (注意：项目不需要这个"时间"icon就默认“200101”，如若不知是否需要，请向SDK提供方咨询)
//                 *
//                 *  相册表盘可以替换原表盘.bin文件中的"时间"icon(设备端会提供多种颜色的icons_xxx.bin文件)
//                 *  也可以替换模板表盘.bin文件中的预览和背景图
//                 *
//                 *  预览图 previewBitmap: 截取在APP界面view中显示的效果图,需裁剪为宽高(例如z51项目设备为：161*182，如若不知，请向SDK提供方索要) 的 Bitmap, 参考图片: dial/replace_img/z51_preview.jpg
//                 *  预览图2 preview2Bitmap: 截取在APP界面view中显示的效果图,需裁剪为宽高(例如z51项目设备为：112*112，如若不知，请向SDK提供方索要) 的 Bitmap, 参考图片: dial/replace_img/z51_preview2.jpg
//                 *  背景图 bgBitmap: 从手机相册或拍照选取,需裁剪为设备屏幕分辨率大小(例如z51项目设备为：240*240，如若不知，请向SDK提供方索要) 的 Bitmap, 参考图片: dial/replace_img/z51_bg.jpg
//                 *
//                 *  项目编码item_code: 用于区分不同项目的逻辑处理(如若不知，请向SDK提供方索要)
//                 *
//                 */
//                //String dialPath = "dial/z75_swatch_2001.bin";//2.相册(自定义)表盘模板文件
//                String dialPath = "dial/z51_swatch_2001.bin";//2.相册(自定义)表盘模板文件
//                String programObject = "200101";//序号后01为"时间"icon位置左上
//                byte[] bytes = FileUtils.byteArraysFromAssets(MainActivity.this, dialPath);//不替换表盘模板内容可直接推送bytes
//                ////////////////下面代码是替换表盘模板内容////////////////
//                //programObject = "200102";//序号后02改为"时间"icon位置右上(如果没有提供多种颜色的icons_xxx.bin文件，就默认 programObject = “200101”)
//                //预览图(请替换为你的项目所需要宽高的.png图片)
//                //byte[] previewDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/z751_preview.png");
//                byte[] previewDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/z51_preview.png");
//                Bitmap previewBitmap = FileUtils.getBitmapFromBytes(previewDataBytes);
//                //预览图2(请替换为你的项目所需要宽高的.png图片，注意：确认项目是否需要这个，不需要或者不支持 preview2Bitmap = null 即可。如若不知，请向SDK提供方咨询)
//                //byte[] preview2DataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/z75_preview2.png");
//                byte[] preview2DataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/z51_preview2.png");
//                Bitmap preview2Bitmap = FileUtils.getBitmapFromBytes(preview2DataBytes);
//                //背景图(请替换为你的项目所需要屏幕分辨率大小的.png图片)
//                //byte[] bgDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/z75_bg.png");
//                byte[] bgDataBytes = FileUtils.byteArraysFromAssets(MainActivity.this, "dial/replace_img/z51_bg.png");
//                Bitmap bgBitmap = FileUtils.getBitmapFromBytes(bgDataBytes);
//                //"时间"icon(请替换为你的项目所需要的.bin文件，如果没有提供就是不需要或者不支持 iconDataBytes = null 即可)
//                byte[] iconDataBytes = null;
//                //项目编码(请替换为你的项目编码，如若不知，请向SDK提供方索要)
//                int item_code = 2022;//2022(z51), 2122(z75)
//                int preview_width = 161;//z51预览图的宽
//                int preview_height = 182;//z51预览图的高
//                int preview2_width = 112;//z51预览图2的宽
//                int preview2_height = 112;//z51预览图2的高
//                int bg_width = 240;//z51背景图的宽
//                int bg_height = 240;//z51背景图的高
//                //设置表盘的项目编码，预览、背景图宽高信息(如若不知有几张预览图、以及对应宽高信息，请咨询SDK提供方)
//                DialSizeInfo dialSizeInfo = null;//DialSizeInfo API请参见doc文档中说明
//                //有2张预览图的如下选择6个参数的构造方法，添加一下第二张预览图宽高
//                dialSizeInfo = new DialSizeInfo(item_code, preview_width, preview_height, preview2_width, preview2_height, bg_width, bg_height);
//                //如果是竖屏横用(竖屏倒过来横着看)预览图和背景图需要旋转对应的角度(如若不知是否竖屏横用，或者旋转对应的角度，请咨询SDK提供方)
//                previewBitmap = SaveBmpUtil.getRotate90Bitmap(-90, previewBitmap);
//                preview2Bitmap = SaveBmpUtil.getRotate90Bitmap(-90, preview2Bitmap);
//                bgBitmap = SaveBmpUtil.getRotate90Bitmap(-90, bgBitmap);
//
//                /*
//                 * 替换相册表盘模板文件内容
//                 * 1. 只替换预览和背景图，iconDataBytes 为 null
//                 * 2. 只替换"时间"icon，previewBitmap、preview2Bitmap和bgBitmap 为 null
//                 * 3. 都替换内容都添加
//                 * 4. 都不替换直接推送bytes，不需要调用replaceAlbumDialData
//                 */
//                bytes = manager.replaceAlbumDialData(bytes, previewBitmap, preview2Bitmap, bgBitmap, iconDataBytes, dialSizeInfo);
//                ////////////////////////推送 相册(自定义)表盘使用代码 END////////////////////////
//
//                //开始推送
//                if (bytes != null) {
//                    /*
//                     * bytes 推送的数据.
//                     * ProgramType.DIAL 推送的数据类型为表盘.
//                     * programObject 序号
//                     */
////                        manager.cmdGet67(bytes, ProgramType.DIAL, programObject);//为防止推送示例.bin与当前设备不匹配，默认注释方法调用
//                    //注: 推送较大数据时, 设备会有缓存擦除等准备操作, 数据越大操作耗时越久, 所以触发推送时可添加一个progress提示，例如：正在读取文件数据...
//                } else {
//                    mPushState.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity.this, "读取数据错误", Toast.LENGTH_SHORT).show();
//                }
//                break;

/************************************************************** G5表盘相关 ********************************************************************/
//            case ProtocolsHeadUtil.SET_DIAL://推送大数据(包括表盘,主题,应用(游戏)等)
//                mPushState.setText("正在读取数据...");
//                mPushState.setVisibility(View.VISIBLE);
//
//                /*
//                 * 注意：
//                 * 以下示例 G5_360_0001.bin文件针对的是项目名为G5_360的设备，如你的项目没有对应的.bin文件请向SDK提供方索要。
//                 * 调试时请把示例对应的所有.bin文件替换为你自己的，不要直接推送与项目不符的.bin文件，会导致设备加载资源出错。
//                 */
//
//                //下面使用assets文件夹中文件进行示范。通常情况下APP文件获取应该在服务器下载或手机本地存储中。
//
//                ////////////////////////推送 预制(固定)表盘使用代码 START////////////////////////
//                /*
//                 * 设置说明：
//                 * 1. 预制(固定)表盘序号取值范围大于“0”小于“2001”，  例: w1_swatch_1.bin, 取1为序号.
//                 */
//                String dialPath = "dial/G5_360_0001.bin";//1.预制(固定)表盘文件
//                String numS = dialPath.split("_")[2].replace(".", ",").split(",")[0];//取到0001
//                String programObject = String.valueOf(Integer.parseInt(numS));//把字符0001转为数值1再转字符1
//                byte[] bytes = FileUtils.byteArraysFromAssets(MainActivity.this, dialPath);
//                ////////////////////////推送 预制(固定)表盘使用代码 END////////////////////////
//
//                //开始推送
//                if (bytes != null) {
//                    /*
//                     * bytes 推送的数据.
//                     * ProgramType.DIAL 推送的数据类型为表盘.
//                     * programObject 序号
//                     */
//                    //manager.cmdGet67(bytes, ProgramType.DIAL, programObject);//为防止推送示例.bin与当前设备不匹配，默认注释方法调用
//                    //注: 推送较大数据时, 设备会有缓存擦除等准备操作, 数据越大操作耗时越久, 所以触发推送时可添加一个progress提示，例如：正在读取文件数据...
//                } else {
//                    mPushState.setVisibility(View.GONE);
//                    Toast.makeText(MainActivity.this, "读取数据错误", Toast.LENGTH_SHORT).show();
//                }
//                break;



        }
    }
}
