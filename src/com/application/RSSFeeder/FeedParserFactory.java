package com.application.RSSFeeder;

  
public abstract class FeedParserFactory {
	
	
	
	public static String feedUrl ="";
	
	public static FeedParser getParser(){
		return getParser(ParserType.ANDROID_SAX);
	}
	
	public static FeedParser getParser(ParserType type){
		switch (type){			
			case ANDROID_SAX:
				return new AndroidSaxFeedParser(feedUrl);			
			default: return null;
		}
	}
}
