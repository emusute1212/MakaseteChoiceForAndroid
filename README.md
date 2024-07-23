# MakaseteChoiceForAndroid

[メンバー組分けアプリまかせて☆チョイス](https://play.google.com/store/apps/details?id=io.github.emusute1212.makasetechoice&pli=1)

## UI

[Figma](https://www.figma.com/design/fwP8u8eFpNkAeQ0SVrV1qu/MakaseteChoice?node-id=39-65&t=YmBXqt0aXeCPzu2N-0)

## アーキテクチャ

[maverick](https://airbnb.io/mavericks/#/)を用いたMVIアーキテクチャを採用している。

登場人物は以下

```mermaid
graph TD;
    subgraph Raw Data layer
        Room
    end
    subgraph Data layer
        Repository
        UseCase
    end
    subgraph UI layer
        ViewModel
        Screen
    end
```

基本的には以下のデータフローとなる

```mermaid
graph TD;
    Screen --①user interaction--> ViewModel
    ViewModel --②get or change data--> Repository
    Repository --③CRUD--> Room
    Room -.④result.-> Repository
    Repository -.⑤result.-> ViewModel
    ViewModel --⑥change states--> ViewModel
    ViewModel --⑦observe states--> Screen
```

複雑なロジックが存在し、UseCaseを通す場合。

```mermaid
graph TD;
    Screen --①user interaction--> ViewModel
    ViewModel --②call logic--> UseCase
    UseCase --③get or change data--> Repository
    Repository --④CRUD--> Room
    Room -.⑤result.-> Repository
    Repository -.⑥result.-> UseCase
    UseCase -.⑦convert result.-> ViewModel
    ViewModel --⑧change states--> ViewModel
    ViewModel --⑨observe states--> Screen
```

## ブランチ戦略

GitHub flowで運用している。

```mermaid
gitGraph
    commit
    branch feature/xxx
    commit
    commit
    checkout main
    merge feature/xxx
    commit
    branch feature/yyy
    commit
    commit
    checkout main
    merge feature/yyy
    commit
    branch release/x.y.z
    commit
    commit
    checkout main
    merge release/x.y.z
```