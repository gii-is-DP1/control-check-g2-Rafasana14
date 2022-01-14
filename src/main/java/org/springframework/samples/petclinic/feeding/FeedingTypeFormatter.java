package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{
	
	private FeedingService fs;
	
	public FeedingTypeFormatter(FeedingService fs) {
		this.fs=fs;
	}

	@Override
	public String print(FeedingType feedingType, Locale locale) {
		return feedingType.getName();
	}

	@Override
	public FeedingType parse(String text, Locale locale) throws ParseException {
		FeedingType ft = fs.getFeedingType(text);
		if(ft!=null) return ft;
		else throw new ParseException("type not found: " + text, 0);
	}
    
}
