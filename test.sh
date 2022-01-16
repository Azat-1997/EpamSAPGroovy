
YOU_API_KEY="Create your own API Key and paste here"
query="https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=viewCount&q=Westboro&maxResults=3&type=video&key=${YOU_API_KEY}"
echo $query

curl --request GET $query \
     --header 'Accept: application/json' \
     --compressed
