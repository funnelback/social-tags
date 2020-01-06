package com.funnelback.common.filter.jsoup;
// Imports for logging
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import com.twitter.Extractor;
/**
* detects #hashtags and @usertags and adds these to custom metadata fields.
*/
public class ExtractSocialTags implements IJSoupFilter {
    private static final Logger logger = LogManager.getLogger(ExtractSocialTags.class)
    /** Configure some metadata field names - this will be used to add a <meta name="fb.custom" /> field */
    public static final String FBUSERTAGS = "fb.usertags";
    public static final String FBHASHTAGS = "fb.hashtags";
    public void processDocument(FilterContext context) {

        // get the document object
        def doc = context.getDocument();

        // extract the body text
        def content = doc.body().text();

        List usertags;
        List hashtags;

        Extractor extractor = new Extractor();

        usertags = extractor.extractMentionedScreennames(content);
        hashtags = extractor.extractHashtags(content);

        logger.error("Extracted usertags: "+usertags.join("|"));
        logger.error("Extracted hashtags: "+hashtags.join("|"));

        context.getAdditionalMetadata().put(FBUSERTAGS, usertags.join("|"));
        context.getAdditionalMetadata().put(FBHASHTAGS, hashtags.join("|"));

    }
}