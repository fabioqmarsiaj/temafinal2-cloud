package com.antoniodanifabio.songservice.commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.antoniodanifabio.songservice.domain.Song;
import com.antoniodanifabio.songservice.repository.SongRepository;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SongsCommand extends HystrixCommand<List<Song>>{
	
	private SongRepository repository;
	
	public SongsCommand(SongRepository repository) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("song"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000)));
		this.repository = repository;
	}

	@Override
	protected List<Song> run() throws Exception {
		return repository.findAll();
	}
	
	@Override
	protected List<Song> getFallback() {
		return null;
	}
	
}