package com.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.FileCopyUtils;

import com.model.Post;
import com.service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class PostServiceTest {

	@Autowired
	private PostService postService;

	@Test
	public void testAdd() throws Throwable {
		Post post = new Post();
		post.setPostText("测试帖子的内容");
		ClassPathResource res = new ClassPathResource("temp.jpg"); // 获取图片资源
		byte[] mockImg = FileCopyUtils.copyToByteArray(res.getFile()); // 读取图片文件的数据
		post.setPostAttach(mockImg);

		postService.add(post);
	}

	@Test
	public void testLoad() {
		Post post = new Post();
		post = postService.load(1);
		if (post != null) {
			System.out.println("   postId = " + post.getId() + "     ,  postText = " + post.getPostText() + "   ,  postAttach.length = "
					+ post.getPostAttach().length);
		} else {
			System.out.println("id不存在！！");
		}
	}

	@Test
	public void testLoadByStream() throws FileNotFoundException {
		FileOutputStream fos = new FileOutputStream("F:/temp.jpg");
		postService.loadByStream(1, fos);
	}
}
