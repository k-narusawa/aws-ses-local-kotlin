CREATE TABLE IF NOT EXISTS ses_mock
(
  `message_id`   VARCHAR(100) PRIMARY KEY,
  `from_address` VARCHAR(100) NOT NULL,
  `to_address`   VARCHAR(100),
  `cc_address`   VARCHAR(100),
  `bcc_address`  VARCHAR(100),
  `subject`      TEXT         NOT NULL,
  `text`         TEXT,
  `html`         TEXT,
  `created_at`   DATETIME     NOT NULL,
  index from_index(`from_address`),
  index from_created_at(`created_at`)
);