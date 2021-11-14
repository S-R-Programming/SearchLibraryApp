# 図書館在庫確認アプリ
このアプリは「借りたい本が近くの図書館にあるか確認できる」Androidアプリです。

## 開発環境 
Android Studio (Java)

## 使い方
トップ画面は以下です。<br><br>
<img src="https://user-images.githubusercontent.com/89324742/141609340-a5c42e32-c960-409d-9c15-14964e0c5b62.png"
     width="400px"><br><br>
ステップ①<br>まず借りたい本のISBN(本に割り当てられたコード)を検索します。本の名前を入力します。<br><br>
<img src="https://user-images.githubusercontent.com/89324742/141609373-b42cb375-163a-4916-b1d4-5b5a03c365f7.png"
     width="400px"><br><br>
   そして「ISBN検索ボタン」を押すとGoogle検索の画面に遷移し、ISBNが確認出来ます。<br><br>
   <img src="https://user-images.githubusercontent.com/89324742/141609422-95d9253e-2e36-4845-b7fa-1a6d72c09f45.png"
        width="400px"><br><br>
        ステップ②<br>そして確認したISBNと地名のローマ字(都道府県_都市名、先頭は大文字)を入力します。
<br><br>
<img src = "https://user-images.githubusercontent.com/89324742/141609458-08f512d6-32b6-41a6-b61b-d4e06b0a14f7.png"
     width="400px"><br><br>
そして「検索ボタン」を押すと蔵書を確認出来る画面に遷移します。<br><br>
<img src = "https://user-images.githubusercontent.com/89324742/141609500-5dcc3048-f9c1-41c7-9a2b-4091756e3eeb.png"
     width="400px"><br><br>
     画面が遷移せず、検索に失敗した場合はもう1度検索するか、「再読み込みボタン」を押してください。
     
## プライバシーポリシー
[ 利用者情報の取得 ]<br>
本アプリが利用者の個人情報を取得することはありません。<br><br>


[ 利用者情報の利用 ]<br>
本アプリが利用者の個人情報を利用することはありません。<br><br>


[ 利用者情報の第三者提供 ]<br>
本アプリが利用者の個人情報を第三者へ提供することはありません。<br><br>


[ 使用ツール ]<br>
本アプリでは、広告配信ツールとしてAdMob(Google Inc.)を使用しており、AdMobが利用者の情報を自動取得する場合があります。 取得する情報、利用目的、第三者への提供等につきましては、広告配信事業者のアプリケーション・プライバシーポリシーよりご確認ください。<br><br>


[ お問い合わせ先 ]<br>
何かご不明な点がございましたら下記からお問い合わせください。<br>
srsrappdeveloper@gmail.com
## 工夫点
・カーリルの図書館API(https://calil.jp/doc/api.html)を使って、在庫情報のJSONファイルを取得しました。

## 課題点
・地名をユーザーにアルファベットで入力させてしまったところ。<br>・デザインがシンプルなものしか出来ません。

## 自己紹介
現在、情報系の学科に通っている大学生です。(2021年11月現在)<br>普段からAndroidアプリの勉強をしています。また、機械学習の勉強もしています。
<br>Qiitaで記事を書いているので、お時間あればこちらもご覧ください。https://qiita.com/S-R-Programming


