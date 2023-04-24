![App Screenshot](https://i.postimg.cc/P547RmQJ/Group-30.webp)


# IGMAPP

## 📎 Releases
***Try latest IGMAPP APK in Releases Page👇***

[![](https://img.shields.io/badge/IGMAPP%20APK-UNDUH-brightgreen?style=for-the-badge&logo=android)](https://github.com/faizul14/IGMAPP/releases/tag/v1.0.0)

## Tech Stack
Dependency Injection (Koin)

Clean Architecture

MVVM

Dependency Injection (Koin)

## API Reference

#### Get Gempabumi Terbaru

```https
  GET https://data.bmkg.go.id/DataMKG/TEWS/autogempa.json
```

#### Get Gempabumi Dirasakan

```https
  GET https://data.bmkg.go.id/DataMKG/TEWS/gempadirasakan.json
```

#### Get Gempabumi M 5.0+

```https
  GET https://data.bmkg.go.id/DataMKG/TEWS/gempaterkini.json
```

#### Get Gambar Shakemap

```https
  GET https://data.bmkg.go.id/DataMKG/TEWS/[kode shakemap].json
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
