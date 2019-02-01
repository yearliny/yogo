package ga.yuan.yogo;

import ga.yuan.yogo.model.enums.ContentStatus;

public class ContentStatusEnumTest {
    public static void main(String[] args) {
        ContentStatus status = ContentStatus.of("publish");
        if (status == ContentStatus.PUBLISH) {
            System.out.println("文章已经发表");
        }
        System.out.println(status);
    }
}
