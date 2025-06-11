# Automatic Programming Repair for MUBench Dataset

This repository contains scripts and/or data related to the [MuBench dataset](https://www.mubench.io/), which is distributed under the Community Research and Academic Programming License ([CRAPL](https://matt.might.net/articles/crapl/)).


## ディレクトリ構成
```
/<project-name>
├── /misuses:                   誤用事例
│   └── /<number>:              コミット内のバグ番号
│       ├── /<_a ~ _f>:         プロンプトパターンAからF (5件全て失敗した場合は削除)
│       |   ├── <1-5>.java:     5回実行したうちの成功事例
│       |   └── prompt.md:      LLMに送信したプロンプト
│       ├── /correct-usages:    元のデータセットに含まれていた修正成功事例
│       ├── bug.java:           バグを含むクラス
│       └── misuse.yml:         誤用事例の詳細
├── /versions:                  自動修正では未使用
└── project.yml:                プロジェクトへのリンク
```

## プロンプトパターン
|     |  バグを含むクラス  |   バグを含む関数  |  Java APIバージョン  |   Java API仕様URL |  独自関数  | Java API仕様一部  |   バグの場所  |  バグの種類  |
|-----|------------------|----------------|---------------------|------------------|----------|------------------|-------------|------------|
| A   |       ⭕️         |                |                     |                  |          |                  |             |            |
| B   |                  |       ⭕️       |                     |                  |          |                  |             |            |
| C   |                  |       ⭕️       |          ⭕️         |         ⭕️        |          |                  |             |            |
| D   |                  |       ⭕️       |                     |                  |    ⭕️     |       ⭕️         |             |            |
| E   |                  |       ⭕️       |                     |                  |          |        ⭕️         |      ⭕️     |            |
| F   |       ⭕️         |                |                     |                  |          |                  |       ⭕️     |     ⭕️     |

# License
This repository uses the [CRAPL License](CRAPL-LICENSE.txt) for the included scripts and data.
**Projects referenced in the dataset** are subject to the licenses of their respective original authors.
