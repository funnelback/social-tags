def q=transaction.question

//Look for hashtags in the query and if found add appropriate metadata constraints
if ((q.query != null) && (q.query != "")) {
    def query_new = "";
    def hl_regex = [];
    for(String kw : q.query.split("\\s+")) {
        if (kw.startsWith("#")) {
            //consider this to be a hashtag
            query_new  += "hashTag:\""+kw.substring(1)+"\" ";
            hl_regex += ["(^|\\s)"+kw+"\\b"];
        }
        else if (kw.startsWith("@")) {
            //consider this to be a hashtag
            query_new  += "userTag:\""+kw.substring(1)+"\" ";
            hl_regex += ["(^|\\s)"+kw+"\\b"];
        }
        else {
            query_new += kw+" ";
        }
    }
    // Original query holds the unmodified query - use this for display
    // If using the default template replace the <@s.QueryClean></@s.QueryClean> macro call 
    // in the result summary with ${question.originalQuery}
    q.originalQuery=q.query;
    // Update the query to use the meta fields
    q.query=query_new;

    // write the custom highlight terms to the data model.  This is combined with the regular highlight regex in the 
    // post datafetch hook script.
    if (hl_regex.size() >0) {
        transaction.response.customData["hl_regex"] = hl_regex
    }
}

