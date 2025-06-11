<img align="right" width="320" height="320" alt="MUBench Logo" src="./meta/logo.png?raw=true" />

# MUBench

MUBench (pronounced "Moo Bench") is an automated benchmark for API-misuse detectors, based on [the MUBench benchmarking dataset](data).
If you encounter any problems using MUBench, please [report them to us](https://github.com/stg-tud/MUBench/issues/new).
If you have any questions, please [contact Sven Amann](http://www.stg.tu-darmstadt.de/staff/sven_amann).

CI Status: [![CI Status](https://api.shippable.com/projects/570d22d52a8192902e1bfa79/badge?branch=master)](https://app.shippable.com/projects/570d22d52a8192902e1bfa79)

## Contributors

The people (in alphabetical order by their last name) who actively contributed to the concept, the dataset, or the codebase of MUBench since the project's inception in October 2015:

* [Sven Amann](http://www.sven-amann.de) (Project Lead, Since 10/2015)
* [Mattis Kämmerer](https://github.com/M8is) (Developer, 03/2016 - 03/2018)
* [Sarah Nadi](http://www.sarahnadi.org/) (Dataset Reviewer, 10/2015 - 05/2016)
* [Hoan Anh Nguyen](https://sites.google.com/site/nguyenanhhoan/) (Dataset Reviewer, 10/2015 - 05/2016)
* [Tien N. Nguyen](http://home.eng.iastate.edu/~tien/) (Dataset Reviewer, 10/2015 - 05/2016)
* [Jonas Schlitzer](https://github.com/joschli) (Developer, 10/2016 - 07/2018)

The MUBench project was developed at the [Software Technology Group of Technische Universität Darmstadt](https://www.stg.tu-darmstadt.de) from October 2015 to September 2018.
From October 2018, it is being maintained by [Sven Amann](http://www.sven-amann.de) as an independent Open Source project.

## Publications

* ['*Exposing Library API Misuses via Mutation Analysis*'](http://home.cse.ust.hk/~mwenaa/paper/ICSE2019A.pdf) ([ICSE '19](https://2019.icse-conferences.org/details/icse-2019-Technical-Papers/56/Exposing-Library-API-Misuses-via-Mutation-Analysis))
* ['*Investigating Next Steps in Static API-Misuse Detection*'](http://sven-amann.de/publications/2019-03-Investigating-Next-Steps-In-Static-API-Misuse-Detection/) (MSR '19)
* ['*A Systematic Evaluation of Static API-Misuse Detectors*'](http://sven-amann.de/publications/2018-03-A-Systematic-Evalution-of-Static-API-Misuse-Detectors/) (TSE '18)
* ['*MUBench: A Benchmark for API-Misuse Detectors*'](http://sven-amann.de/publications/2016-05-MSR-MUBench-dataset.html) ([MSR '16 Data Showcase](http://2016.msrconf.org/#/data))

We provide [instructions to reproduce the MUBench experiments](reproduction/) presented in the above publications.

## Getting Started

With MUBench, you may run [different API-misuse detectors](detectors/#mubench---detectors) in [a number of experiments](mubench.pipeline/#experiments) to determine their precision and recall.
To this end, MUBench provides [a curated dataset of real-world projects and known misuses](data/#mubench---dataset).
In each experiment run, the respective detector emits findings which you need to review manually.
To this end, MUBench publishes (a subset of) the findings to [a review website](mubench.reviewsite/#mubench---review-website).
After you completed your reviews, the site automatically computes experiment statistics.

### Setup

1. MUBench comes with a [Docker](https://www.docker.com/) image to allow platform-independent execution of experiments.
   Therefore, you first need to [install Docker](https://www.docker.com/get-started).
2. Afterwards, you can start the MUBench Interactive Shell using
   
       $> docker run -it -v mubench-checkouts:/mubench/checkouts -v mubench-findings:/mubench/findings --rm -p 8080:80 svamann/mubench:stable
   
   This docker command starts in interactive shell (`-it`) based on our Docker image `svamann/mubench` in the latest `stable` version.
   It persists experiment data on [Docker volumes](https://docs.docker.com/storage/volumes/) named `mubench-checkouts` and `mubench-findings` (`-v`), while disposing of all other state on exit (`--rm`).
   And it forwards port `80` from within the shell to port `8080` of your host system, to allow running a standalone review site.
3. (Optional) You may want to create [a script with the above command](mubench.bin/mubench), which allows you to conveniently open a MUBench Interactive Shell by typing `./mubench` or running individual commands by typing `./mubench <command>`.
4. (Optional) You may want to [setup a review site](mubench.reviewsite/#setup) to collaboratively review detector findings and publish your results.

*Hint*: We recommend to use the latest stable version `svamann/mubench:stable` of our Docker image.
However, you may also chose to use the latest development version `svamann/mubench:latest`, which is continuously deployed from the `master` branch of this repository.

### Use

For all usage examples in this documentation, we assume that you [opened a MUBench Interactive Shell](#setup).
Alternatively, you may execute individual commands by passing them as arguments to [the docker command for running MUBench](#setup).

1. [Run experiments](mubench.pipeline/#run-experiments).
2. [Publish misuse metadata to a review site](mubench.reviewsite/#publish-misuse-metadata).
2. [Publish detector findings to a review site](mubench.reviewsite/#publish-detector-findings).
3. [Debug a detector (runner)](mubench.cli/#debugging-a-detector).

## Contribute

We want MUBench to grow, so please be welcome to

* [Add your own projects or misuses to the benchmarking dataset](data/#contribute).
* [Add your own detector to the benchmark](mubench.cli/#mubench---detector-interface).
* [Contribute to the benchmarking platform](CONTRIBUTE.md)

## License

All software provided in this repository is subject to the [CRAPL license](CRAPL-LICENSE.txt).

The detectors included in MuBench are subject to the licensing of their respective creators. See the information in [the detectors' folders](detectors).

The projects referenced in the MuBench dataset are subject to their respective licenses.

The project artwork is subject to the [Creative Commons Attribution-ShareAlike 4.0 International (CC BY-SA 4.0)](https://creativecommons.org/licenses/by-sa/4.0/).
