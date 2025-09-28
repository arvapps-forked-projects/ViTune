import yt_dlp
import json

def download(video_id):
    opts = {
        'format': 'bestaudio'
    }

    return json.dumps(yt_dlp.YoutubeDL(opts).extract_info(video_id, download=False), indent=4)
