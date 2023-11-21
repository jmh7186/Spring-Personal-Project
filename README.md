# ポートフォリオ By JO MINHYEOK
Spring Bootベース WEB


[リンク][]

# link define
[リンク]: http://127.0.0.1/

## 開発環境
- **Java / JDK Version :** 17
- **IDE :** STS 4
- **Framework :** Spring Boot 3
- **DB :** Oracle DB → H2 DB
- **ORM :** MyBatis

## 主要機能
- **ログイン :** ＤＢ対照後登録されている情報と一致したらログインアクセス、セッションにログイン状態登録。
- **会員登録 :** 会員登録ページへ移動して情報を入力したら有効性検査後ＤＢにデータインサート。
- **ログインフィルター :** 掲示板、チャットページはセッションがログイン状態ではない場合ログインページへリダイレクトさせる。
- **各国旅行情報 :** 韓国領事館から提供されるAPIを利用して各国の旅行情報を読み込んで標示するサービス。
