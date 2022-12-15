# IGMAPP


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
