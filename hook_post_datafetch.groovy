// Updates the highlight pattern to ensure social tags are highlighted.

if ( transaction.response != null
  && transaction.response.resultPacket != null) {

  // Update the highlight regex to include hash/user tags
  if (transaction.response.resultPacket.queryHighlightRegex != null) {
    if (( transaction.response.customData["hl_regex"] != null) && (transaction.response.customData["hl_regex"].size() > 0 )) {
      transaction.response.resultPacket.queryHighlightRegex+="|"+transaction.response.customData["hl_regex"].join("|")
    }
  }
  else {
     if (( transaction.response.customData["hl_regex"] != null) && (transaction.response.customData["hl_regex"].size() > 0 )) {
      transaction.response.resultPacket.queryHighlightRegex="(?i)"+transaction.response.customData["hl_regex"].join("|")
    }
  }
}

