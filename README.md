# Social tags

This code adds support for the extraction of #hashtags and @usertags in page content and also adds the ability to search for these tags by entering the tag into the search box.

# Documentation

This code adds support for the extraction of #hashtags and @usertags in page content and also adds the ability to search for these tags by entering the tag into the search box.

## Installation

Installation of the code requires backend access to the Funnelback server.

1. Copy the `ExtractSocialTags.groovy` Jsoup filter to the following folder, replacing COLLECTION with the name of the collection you are wishing to extract the social tags on: `$SEARCH_HOME/conf/COLLECTION/@groovy/filter/jsoup/`.    This must be the collection that gathers the content (not a meta collection).   If the folder doesn't exist you will need to create it.  

2. Copy the `hook_post_datafetch.groovy` and `hook_pre_process.groovy` files to the `$SEARCH_HOME/conf/COLLECTION/` folder of the collection that you will be issuing queries against (look for the collection= parameter when you run a query).  Note: if your content is in a meta collection you will probably need to copy it to this collection.

3. Update the `collection.cfg` of the gather collection, adding `jsoup.filter.ExtractSocialTags` to the `jsoup.filter.classes`.  Ensure you add it to the other filters that are running.

e.g.

```
filter.jsoup.classes=ContentGeneratorUrlDetection,FleschKincaidGradeLevel,UndesirableText,filter.jsoup.scrapeMetadata
```

4. Update the metamap.cfg  of the gather collection to include the following metadata mappings:

```
hashTag,1,fb.hashtags
userTag,1,fb.usertags
```

5. Run a full update of your collection.  A full update is required to ensure that all the content is re-gathered and filtered.

## Push collections

This code is also compatible with push collections.  Setup is the same as above.  In addition, when making any API calls to add or update content the filters will need to be specified in the call.

The filters must include the Jsoup filter (`JSoupProcessingFilterProvider`) and will be something like the following:

```
FAChecker:CombinerFilterProvider,TikaFilterProvider,ExternalFilterProvider:JSoupProcessingFilterProvider:DocumentFixerFilterProvider
```

# Dependencies

The following third party code is used for this implementation:

* [Twitter text library](https://github.com/twitter/twitter-text)
