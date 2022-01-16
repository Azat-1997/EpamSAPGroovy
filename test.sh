
YOU_API_KEY="AIzaSyCxFZ0FLBO7z_ENKLTtvNTDcC8BHwUsymA"
query="https://youtube.googleapis.com/youtube/v3/search?part=snippet&order=viewCount&q=Westboro&type=video&key=${YOU_API_KEY}"
echo $query

curl --request GET $query \
     --header 'Accept: application/json' \
     --compressed
