package com.amirmohammed.androidultrassat.models;

import java.util.List;

public class PostResponse{
	private List<Post> postResponse;

	public void setPostResponse(List<Post> postResponse){
		this.postResponse = postResponse;
	}

	public List<Post> getPostResponse(){
		return postResponse;
	}

	@Override
 	public String toString(){
		return 
			"PostResponse{" + 
			"postResponse = '" + postResponse + '\'' + 
			"}";
		}
}