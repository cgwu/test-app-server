package com.wyjf.app.repository;

import com.wyjf.common.domain.User;
import com.wyjf.common.domain.UserInfo;
import com.wyjf.common.repository.UserInfoRepo;
import com.wyjf.common.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2017/9/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepoTest {
    private static final Logger log = LoggerFactory.getLogger(UserInfoRepoTest.class);

    @Autowired
    private UserInfoRepo repo;

    /**
     * NIO way
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readFileToByteArray(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSave() {

        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream("F:\\workspace\\screen.png"));

            byte[] buf = readFileToByteArray("F:\\workspace\\screen.png");

            UserInfo info = new UserInfo();
            info.setUid(3L);
            info.setHeadThumb(buf);
            repo.save(info);

            log.info("保存UserInfo成功！");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testReadFromDB() {
        UserInfo info = repo.findOne(3L);
        if (info != null) {
            try {
                FileOutputStream out = new FileOutputStream("F:\\workspace\\screen-read-from-db.png");
                out.write(info.getHeadThumb());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
