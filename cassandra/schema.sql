create table spark_demo.prices
(
    timestamp  int primary key,
    close      text,
    error_code text,
    high       text,
    low        text,
    open       text
)
    with caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
        and compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
        and compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
        and dclocal_read_repair_chance = 0.1;

