package ga.yuan.yogo.model.enums;

/**
 * content 类型
 * 文章：post
 * 页面：page
 * 附件：attachment，可以是图片或其他上传的文件
 * 版本：revision，一篇文章或页面，保存多个版本
 */
public enum ContentTypeEnum {
    POST, PAGE, ATTACHMENT, REVISION;
}